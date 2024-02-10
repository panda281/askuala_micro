package et.com.gebeya.askuala_school_management_management_service.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponseDto {
    private Integer id;
    private Integer studentId;
    private Integer teacherId;
    private String attendanceDate;
    private String status;
    private String remark;

}
