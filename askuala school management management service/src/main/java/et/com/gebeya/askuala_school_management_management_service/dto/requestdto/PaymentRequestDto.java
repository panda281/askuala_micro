package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;

import et.com.gebeya.askuala_school_management_management_service.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

public class PaymentRequestDto {
    private String studentId;
    private Double price;
    private PaymentStatus paymentStatus;
}
