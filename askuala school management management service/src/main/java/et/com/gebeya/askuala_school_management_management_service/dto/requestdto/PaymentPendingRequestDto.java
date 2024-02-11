package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PaymentPendingRequestDto {
    private String email;
    private String name;
    private Double price;
    private String month;
}