package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;


import et.com.gebeya.askuala_school_management_management_service.enums.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentRequestDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private String studentId;
    private Gender gender;
    private AddressDto addressDto;
    private Grade grade;
    private Section Section;
    private Set<GuardianDto> guardian;
}
