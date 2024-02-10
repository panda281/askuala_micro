package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AttendanceRequestDto {
    private Integer studentId;
    private Date attendanceDate;
    private String status;
    private String remark;
}
