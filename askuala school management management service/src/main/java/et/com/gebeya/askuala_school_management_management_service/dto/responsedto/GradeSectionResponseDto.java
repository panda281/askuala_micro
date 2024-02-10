package et.com.gebeya.askuala_school_management_management_service.dto.responsedto;


import et.com.gebeya.askuala_school_management_management_service.enums.Gender;
import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeSectionResponseDto {
    private Integer id;
    private Grade grade;
    private Section section;
    private String homeRoomTeacherName;
    private Gender gender;
}
