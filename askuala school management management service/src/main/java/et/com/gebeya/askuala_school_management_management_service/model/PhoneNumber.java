package et.com.gebeya.askuala_school_management_management_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Builder
public class PhoneNumber extends BaseModel{

    @Column(length = 14)
    @Pattern(regexp = "^\\+251\\d{9}$")
    private String phoneNumber;

}
