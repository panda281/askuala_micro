package et.com.gebeya.askuala_school_management_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Teacher extends Person {

    private String qualifications;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private Address address;


//    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST}, mappedBy = "teachers")
////    @NotEmpty
//    private List<Subject> subject;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Set<TeacherSubject> teacherSubjects;






}
