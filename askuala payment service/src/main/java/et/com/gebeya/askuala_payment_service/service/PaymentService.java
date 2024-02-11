package et.com.gebeya.askuala_payment_service.service;

import et.com.gebeya.askuala_payment_service.dto.requestdto.PaymentRequestDto;
import et.com.gebeya.askuala_payment_service.dto.responsedto.PaymentResponseDto;
import et.com.gebeya.askuala_payment_service.model.Payment;
import et.com.gebeya.askuala_payment_service.repository.PaymentRepository;
import et.com.gebeya.askuala_payment_service.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public Payment createPayment(PaymentRequestDto dto)
    {
        Payment payment = MappingUtil.mappingPaymentDtoToModel(dto);
        return payment=paymentRepository.save(payment);

    }
}
