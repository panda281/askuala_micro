package et.com.gebeya.askualas_gateway.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor

public class ValidationResponseDto {
    private final Authority role;
    private final Integer roleId;
}
