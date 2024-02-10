package et.com.gebeya.askuala_school_management_management_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;



@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Guardian extends Person{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    @Valid
    private Address address;

    @ManyToMany(mappedBy = "guardian")
    @JsonIgnoreProperties("guardian")
    private List<Student> studentList;
}
