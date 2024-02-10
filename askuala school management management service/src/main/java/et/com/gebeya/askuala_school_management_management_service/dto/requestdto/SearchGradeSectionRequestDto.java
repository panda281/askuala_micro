package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;

import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import lombok.Data;

import java.util.Map;

@Data
public class SearchGradeSectionRequestDto {
    private final Integer teacherId;
    private final Grade grade;
    private final String teacherFirstName;


    private static final String teacherIdParam = "teacherId";
    private static final String gradeParam = "grade";
    private static final String teacherFirstNameParam = "teacherFirstName";

    public SearchGradeSectionRequestDto(Map<String,String> request)
    {
        if(request==null)
        {
            teacherId = 0;
            grade = null;
            teacherFirstName = null;
            return;
        }


        String teacherIdValue = request.get(teacherIdParam);
        String teacherFirstNameValue = request.get(teacherFirstNameParam);
        String gradeValue = request.get(gradeParam);

        this.teacherId = teacherIdValue != null ? Integer.parseInt(teacherIdValue) : 0;
        this.teacherFirstName = teacherFirstNameValue;
        this.grade = gradeValue != null ? Grade.valueOf(gradeValue) : null;
    }
}
