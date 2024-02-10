package et.com.gebeya.askuala_school_management_management_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attendance extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    private Teacher teacher;
    @NotBlank(message = "AttendanceDate is mandatory")
    private Date attendanceDate;
    @Column(length = 255)
    @NotBlank(message = "Status is mandatory")
    private String status;
    @Column(length = 255)
    private String remark;


}
