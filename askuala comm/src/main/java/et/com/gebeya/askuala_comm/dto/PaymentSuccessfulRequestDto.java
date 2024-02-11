package et.com.gebeya.askuala_comm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class PaymentSuccessfulRequestDto {
    private String name;
    private String email;
    private String month;
}
