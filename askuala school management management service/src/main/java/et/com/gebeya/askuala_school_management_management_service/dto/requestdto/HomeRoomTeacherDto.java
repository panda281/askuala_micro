package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HomeRoomTeacherDto {
    private Integer teacherId;
    private Integer gradeSectionId;

}
