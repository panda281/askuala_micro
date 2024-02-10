package et.com.gebeya.askuala_school_management_management_service.model;

import et.com.gebeya.askuala_school_management_management_service.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
//@Builder
public class Person extends BaseModel{
    @Column(length = 255)
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @Column(length = 255)
    @NotBlank(message = "MiddleName is mandatory")
    private String middleName;
    @Column(length = 255)
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Gender is mandatory")
    private Gender gender;
}
