package et.com.gebeya.askuala_school_management_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "teacher_subject")
public class TeacherSubject {
    @EmbeddedId
    @JsonIgnore
    private TeacherSubjectId id;

    @ManyToOne
    @MapsId("teacherId")
    private Teacher teacher;

    @ManyToOne
    @MapsId("subjectId")
    private Subject subject;

    @ManyToOne
    @MapsId("gradeSectionId")
    private GradeSection gradeSection;




}

