package et.com.gebeya.askuala_school_management_management_service.dto.responsedto;


import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import et.com.gebeya.askuala_school_management_management_service.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeSectionResponse {
    private Integer id;
    private Grade grade;
    private Section section;
    private Set<Subject> subjectList;
}
