package et.com.gebeya.askuala_payment_service.listener;

import et.com.gebeya.askuala_payment_service.dto.requestdto.PaymentRequestDto;
import et.com.gebeya.askuala_payment_service.model.Payment;
import et.com.gebeya.askuala_payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {
    private final PaymentService paymentService;

    @KafkaListener(topics = "PAYMENT", groupId = "group1",containerFactory = "paymentListenerFactory")
    void Listner(PaymentRequestDto requestDto)
    {
        System.out.println(requestDto);
        Payment payment = paymentService.createPayment(requestDto);
        System.out.println(payment);
    }
}
