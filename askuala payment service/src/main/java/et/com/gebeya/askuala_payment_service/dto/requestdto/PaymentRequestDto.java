package et.com.gebeya.askuala_payment_service.dto.requestdto;

import et.com.gebeya.askuala_payment_service.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentRequestDto {
    private String studentId;
    private Double price;
}
