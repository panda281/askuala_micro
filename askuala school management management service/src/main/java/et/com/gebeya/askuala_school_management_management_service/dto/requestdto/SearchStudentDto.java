package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;


import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import lombok.Data;

import java.util.Map;

@Data
public class SearchStudentDto {
    private String firstName;
    private Grade grade;
    private Section section;

    private static String firstNameParam = "firstName";
    private static String gradeParam = "grade";

    public SearchStudentDto(Map<String,String> search)
    {
        if(search==null)
        {
            firstName=null;
            grade=null;
            section=null;
            return;
        }

        String firstNameValue = search.get(firstNameParam);
        String grade = search.get(gradeParam);
        this.firstName = firstNameValue;

        if(grade!=null)
        {
            this.grade = Grade.valueOf(grade.substring(0,grade.length()-1));
            this.section= Section.valueOf(grade.substring(grade.length()-1));
        }
        else{
            this.grade=null;
            this.section=null;
        }




    }
}
