package et.com.gebeya.askuala_school_management_management_service.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    String role;
    String userName;
    String password;
    String email;
}
