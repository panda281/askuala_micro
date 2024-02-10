package et.com.gebeya.askuala_school_management_management_service.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllAttendanceResponseDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String studentId;
    private String attendanceDate;
    private String status;
    private String remark;
}
