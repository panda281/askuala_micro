package et.com.gebeya.askuala_school_management_management_service.service;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.AddAccountRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.PaymentPendingRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.PaymentRequestDto;
import et.com.gebeya.askuala_school_management_management_service.enums.PaymentStatus;
import et.com.gebeya.askuala_school_management_management_service.model.Guardian;
import et.com.gebeya.askuala_school_management_management_service.model.Student;
import et.com.gebeya.askuala_school_management_management_service.repository.StudentRepository;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static et.com.gebeya.askuala_school_management_management_service.util.Constant.PAYMENT_PENDING_TOPIC;
import static et.com.gebeya.askuala_school_management_management_service.util.Constant.PAYMENT_TOPIC;

@Service
public class PaymentService {

    private final KafkaTemplate<String, PaymentRequestDto> paymentRequestDtoKafkaTemplate;
    private final KafkaTemplate<String, PaymentPendingRequestDto> paymentPendingRequestDtoKafkaTemplate;
    private final StudentRepository studentRepository;
    private final Map<Integer, Double> gradeMonthlyPrice = new HashMap<>();


    public PaymentService(KafkaTemplate<String, PaymentRequestDto> paymentRequestDtoKafkaTemplate, StudentRepository studentRepository,KafkaTemplate<String, PaymentPendingRequestDto> paymentPendingRequestDtoKafkaTemplate) {
        this.paymentRequestDtoKafkaTemplate = paymentRequestDtoKafkaTemplate;
        this.studentRepository = studentRepository;
        this.paymentPendingRequestDtoKafkaTemplate = paymentPendingRequestDtoKafkaTemplate;
        gradeMonthlyPrice.put(1, 1000.00);
        gradeMonthlyPrice.put(2, 1500.00);
        gradeMonthlyPrice.put(3, 2000.00);
        gradeMonthlyPrice.put(4, 2500.00);
        gradeMonthlyPrice.put(5, 3000.00);
        gradeMonthlyPrice.put(6, 3500.00);
        gradeMonthlyPrice.put(7, 4000.00);
        gradeMonthlyPrice.put(8, 4500.00);
        gradeMonthlyPrice.put(9, 5000.00);
        gradeMonthlyPrice.put(10, 5500.00);
        gradeMonthlyPrice.put(11, 6000.00);
        gradeMonthlyPrice.put(12, 6500.00);
    }

    @Scheduled(cron = "* * * * * *")
    public void scheduledTask() {
        studentRepository.findAll()
                .forEach(this::sender);
    }

    public void sender(Student student) {
        try {
            PaymentRequestDto request = PaymentRequestDto.builder()
                    .studentId(student.getStudentId())
                    .price(gradeMonthlyPrice.get(gradeNumberExtractor(student.getGradeSection().getGrade().name())))
                    .paymentStatus(PaymentStatus.PENDING)
                    .build();

            List<Guardian> guardians = new ArrayList<>(student.getGuardian());
            LocalDate currentDate = LocalDate.now();
            Month currentMonth = currentDate.getMonth();
            PaymentPendingRequestDto payment = PaymentPendingRequestDto.builder()
                    .email(guardians.get(0).getAddress().getEmail())
                    .month(String.valueOf(currentMonth))
                    .price(gradeMonthlyPrice.get(gradeNumberExtractor(student.getGradeSection().getGrade().name())))
                    .name(student.getFirstName())
                    .build();
            paymentPendingRequestDtoKafkaTemplate.send(PAYMENT_PENDING_TOPIC,payment);
            paymentRequestDtoKafkaTemplate.send(PAYMENT_TOPIC, request);
        } catch (KafkaProducerException e) {
            throw new RuntimeException(e.getMessage());
        }

    }



    private Integer gradeNumberExtractor(String grade) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(grade);

        if (matcher.find()) {
            String numberStr = matcher.group();
            return Integer.parseInt(numberStr);
        }

        return null; // or handle the case when no number is found
    }

}
