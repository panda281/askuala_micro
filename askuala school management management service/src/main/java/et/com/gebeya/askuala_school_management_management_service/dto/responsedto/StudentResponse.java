package et.com.gebeya.askuala_school_management_management_service.dto.responsedto;


import et.com.gebeya.askuala_school_management_management_service.enums.Gender;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private String studentId;
    private Gender gender;
    private Boolean isActive;
    private Integer addressId;
    private Integer gradeSection;
}
