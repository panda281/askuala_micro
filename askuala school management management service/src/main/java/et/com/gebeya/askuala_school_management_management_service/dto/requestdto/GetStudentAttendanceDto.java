package et.com.gebeya.askuala_school_management_management_service.dto.requestdto;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Data

public class GetStudentAttendanceDto {

    private final Date startDate;
    private final Date endDate;

    private static final String startDateParam = "startDate";
    private static final String endDateParam = "endDate";

    public GetStudentAttendanceDto(Map<String, String> request) {
        if (request==null) {
            this.startDate = new Date();
            this.endDate = new Date();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        String startDateValue = request.get(startDateParam);
        String endDateValue = request.get(endDateParam);

        if (startDateValue == null || startDateValue.isEmpty()) {
            this.startDate = new Date();
        } else {
            try {
                this.startDate = dateFormat.parse(startDateValue);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format");
            }
        }

        if (endDateValue == null || endDateValue.isEmpty()) {
            this.endDate = new Date();
        } else {
            try {
                this.endDate = dateFormat.parse(endDateValue);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format");
            }
        }
    }
}