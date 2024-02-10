package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;

import et.com.gebeya.askuala_school_management_management_service.enums.Gender;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TeacherRequestDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private String qualifications;
    private AddressDto addressDto;
    private Set<Integer> subject;
    private Set<Integer> gradeSection;


}
