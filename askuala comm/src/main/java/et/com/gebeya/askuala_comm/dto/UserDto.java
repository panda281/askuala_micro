package et.com.gebeya.askuala_comm.dto;

import et.com.gebeya.askuala_comm.telegram.UserState;
import lombok.*;


@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private UserState state;
    private String username;
    private Long receiverChatId;



}