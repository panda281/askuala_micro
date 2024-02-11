package et.com.gebeya.askuala_comm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class PaymentPendingRequestDto {
    private String email;
    private String name;
    private Double price;
    private String month;
}
