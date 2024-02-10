package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;


import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import lombok.*;

import java.util.Set;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GradeSectionRequestDto {
    private Grade grade;
    private Section section;
    private Set<Integer> subjectId;
}
