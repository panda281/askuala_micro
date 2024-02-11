package et.com.gebeya.askuala_payment_service.dto.responsedto;

import et.com.gebeya.askuala_payment_service.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@Builder
public class PaymentResponseDto {
    private String studentId;
    private Double price;
    private PaymentStatus paymentStatus;
}
