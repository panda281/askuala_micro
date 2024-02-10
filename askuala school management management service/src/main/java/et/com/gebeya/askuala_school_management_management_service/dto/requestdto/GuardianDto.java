package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;


import et.com.gebeya.askuala_school_management_management_service.enums.Gender;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class GuardianDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private AddressDto addressDto;
}