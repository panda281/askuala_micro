package et.com.gebeya.askuala_school_management_management_service.controller;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.AttendanceRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.AttendanceResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GetAllAttendanceResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GetStudentAttendanceResponseDto;
import et.com.gebeya.askuala_school_management_management_service.service.AttendanceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/school/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/attendances/student")
    public ResponseEntity<List<GetStudentAttendanceResponseDto>> getStudentAttendanceByParam(@RequestParam(required = false) Map<String, String> search, @PageableDefault(page = 0, size = 10) Pageable pageable, HttpServletRequest request){
        return ResponseEntity.ok(attendanceService.getStudentAttendance(search,pageable,request));
    }
    @GetMapping("/attendances/teacher")
    public ResponseEntity<List<GetAllAttendanceResponseDto>> getTeacherAttendance(HttpServletRequest request){
        return ResponseEntity.ok(attendanceService.getAllAttendanceByTeacherId(request));
    }
    @PostMapping("/attendances")
    public ResponseEntity<AttendanceResponseDto> addAttendance(HttpServletRequest request, @RequestBody AttendanceRequestDto attendanceRequestDto){
        return ResponseEntity.ok(attendanceService.addAttendance(request, attendanceRequestDto));
    }
}
