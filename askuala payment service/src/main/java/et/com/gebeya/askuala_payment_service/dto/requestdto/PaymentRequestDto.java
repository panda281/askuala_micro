package et.com.gebeya.askuala_payment_service.dto.requestdto;

import et.com.gebeya.askuala_payment_service.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@Builder
public class PaymentRequestDto {
    private String studentId;
    private PaymentStatus paymentStatus;
    private BigDecimal price;
}
