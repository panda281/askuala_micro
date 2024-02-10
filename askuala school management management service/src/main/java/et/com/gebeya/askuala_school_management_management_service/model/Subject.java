package et.com.gebeya.askuala_school_management_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"teachersList", "gradeSectionsList"})
@Builder
public class Subject extends BaseModel{

    @Column(length = 255)
    @NotBlank(message = "SubjectName is mandatory")
    private String name;

//    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("subject")
//    private List<Teacher> teachers;

//    @JsonIgnoreProperties("subject")
    @JsonIgnore
    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<TeacherSubject> teacherSubjects;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<GradeSection> gradeSectionsList;




}
