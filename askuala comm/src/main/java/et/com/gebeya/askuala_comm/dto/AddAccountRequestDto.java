package et.com.gebeya.askuala_comm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class AddAccountRequestDto {
    private String name;
    private String email;
    private String username;
    private String password;
}
