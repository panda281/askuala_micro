package et.com.gebeya.askuala_auth_service.dto.response_dto;


import et.com.gebeya.askuala_auth_service.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    private Authority authority;
}
