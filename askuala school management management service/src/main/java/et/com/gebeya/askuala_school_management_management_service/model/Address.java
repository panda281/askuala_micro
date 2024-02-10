package et.com.gebeya.askuala_school_management_management_service.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Address extends BaseModel{
    @Column(length = 255)
    @Email(message = "Email is mandatory")
    private String email;
    @Column(length = 255)
    @NotBlank(message = "City is mandatory")
    private String city;
    @Column(length = 255)
    @NotBlank(message = "SubCity is mandatory")
    private String subCity;
    @Column(length = 255)
    @NotBlank(message = "Wereda is mandatory")
    private String wereda;
    @Column(length = 255)
    @NotBlank(message = "HouseNumber is mandatory")
    private String houseNumber;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    @NotEmpty
    private List<PhoneNumber> phoneNumber;


}
