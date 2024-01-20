package et.com.gebeya.askuala_payment_service.controller;

import et.com.gebeya.askuala_payment_service.dto.requestdto.PaymentRequestDto;
import et.com.gebeya.askuala_payment_service.dto.responsedto.PaymentResponseDto;
import et.com.gebeya.askuala_payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto request)
    {
        return paymentService.createPayment(request);
    }
}
