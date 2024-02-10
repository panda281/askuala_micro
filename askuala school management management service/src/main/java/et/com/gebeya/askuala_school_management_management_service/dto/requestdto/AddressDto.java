package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddressDto {
    private String email;
    private String city;
    private String subCity;
    private String wereda;
    private String houseNumber;
    private List<PhoneNumberDto> phoneNumber;
}
