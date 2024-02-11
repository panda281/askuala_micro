package et.com.gebeya.askuala_school_management_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.util.Date;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Student extends Person{

    @Past
    private Date dob;
    @Column(length = 50)
    @NotBlank(message = "StudentId is mandatory")
    private String studentId;
//    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
    @Valid
    private Address address;
    @ManyToOne
    @Valid
    private GradeSection gradeSection;

    @JsonIgnoreProperties("studentList")
//    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_gurdian",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "gurdian_id")
    )
    @NotEmpty(message = "Guardians are a mandatory")
    private Set<Guardian> guardian;

}
