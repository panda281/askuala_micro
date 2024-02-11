package et.com.gebeya.askuala_comm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class UpdateAccountRequestDto {
    private String name;
    private String email;
}
