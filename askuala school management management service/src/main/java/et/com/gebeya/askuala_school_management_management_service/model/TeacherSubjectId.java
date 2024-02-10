package et.com.gebeya.askuala_school_management_management_service.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubjectId implements Serializable {
    private Integer teacherId;
    private Integer subjectId;
    private Integer gradeSectionId;
}
