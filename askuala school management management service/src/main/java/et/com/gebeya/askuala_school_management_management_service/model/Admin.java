package et.com.gebeya.askuala_school_management_management_service.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Admin extends Person{



    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    @Valid
    private Address address;


}
