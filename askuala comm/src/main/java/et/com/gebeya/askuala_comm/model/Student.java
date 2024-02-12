package et.com.gebeya.askuala_comm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Instant createdOn;
    private Instant updatedOn;
    private Boolean isActive;
    private String gender;
    private Date dob;
    private String studentId;
    private Integer addressId;
    private Integer gradeSectionId;

}
