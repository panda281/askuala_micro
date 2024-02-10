package et.com.gebeya.askuala_school_management_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "teachersList")
public class GradeSection extends BaseModel{

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Grade is mandatory")
    private Grade grade;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Section is mandatory")
    private Section section;
    @OneToOne()
    @Nullable
    private Teacher homeRoomTeacher;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "grade_section_subject",
            joinColumns = @JoinColumn(name = "grade_section_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )

    @JsonIgnore
    private Set<Subject> subjects;


//    @JsonIgnoreProperties("gradeSection")
    @JsonIgnore
    @OneToMany(mappedBy = "gradeSection",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<TeacherSubject> teacherSubject;


}
