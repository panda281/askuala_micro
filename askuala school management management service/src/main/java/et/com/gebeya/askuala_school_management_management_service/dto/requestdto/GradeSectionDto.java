package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;


import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GradeSectionDto {
    private Grade grade;
    private Section section;
}
