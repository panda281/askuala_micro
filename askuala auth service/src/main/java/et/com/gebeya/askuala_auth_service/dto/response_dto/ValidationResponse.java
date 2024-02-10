package et.com.gebeya.askuala_auth_service.dto.response_dto;


import et.com.gebeya.askuala_auth_service.enums.Authority;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResponse {
        private final Authority role;
        private final Integer roleId;
}
