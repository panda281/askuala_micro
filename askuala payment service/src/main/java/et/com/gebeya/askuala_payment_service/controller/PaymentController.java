package et.com.gebeya.askuala_payment_service.controller;

import et.com.gebeya.askuala_payment_service.dto.requestdto.PaymentRequestDto;
import et.com.gebeya.askuala_payment_service.dto.responsedto.PaymentResponseDto;
import et.com.gebeya.askuala_payment_service.model.Payment;
import et.com.gebeya.askuala_payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/createpayment")
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequestDto request)
    {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String>test()
    {
        return ResponseEntity.ok("test 123");
    }
}
