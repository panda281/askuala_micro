package et.com.gebeya.askuala_school_management_management_service.service;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.AttendanceRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.GetStudentAttendanceDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.AttendanceResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GetAllAttendanceResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GetStudentAttendanceResponseDto;
import et.com.gebeya.askuala_school_management_management_service.model.Attendance;
import et.com.gebeya.askuala_school_management_management_service.model.Student;
import et.com.gebeya.askuala_school_management_management_service.model.Teacher;
import et.com.gebeya.askuala_school_management_management_service.repository.AttendanceRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.StudentRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.TeacherRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.specifications.AttendanceSpecifications;
import et.com.gebeya.askuala_school_management_management_service.util.MappingUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private Student getStudent(Integer id)
    {
        return studentRepository.findById(id).orElseThrow(()->new RuntimeException(id + " can't not be found"));
    }

    private Teacher getTeacher(Integer id)
    {
        return teacherRepository.findById(id).orElseThrow(()->new RuntimeException(id + " can't not be found"));
    }
    public AttendanceResponseDto addAttendance(HttpServletRequest request, AttendanceRequestDto dto)
    {
        Attendance attendance = MappingUtil.AttendanceDtoToModel(dto);
        attendance.setStudent(getStudent(dto.getStudentId()));
        attendance.setTeacher(getTeacher((Integer) request.getAttribute("userId")));
        return MappingUtil.AttendanceToAttendanceResponseDto(attendanceRepository.save(attendance));
    }
    public List<GetAllAttendanceResponseDto> getAllAttendanceByTeacherId(HttpServletRequest request)
    {
        Integer id = (Integer) request.getAttribute("userId");
        List<Object[]> map = attendanceRepository.getAllAttendanceByTeacherId(id);
        return map.stream()
                .map(result->{
                    GetAllAttendanceResponseDto response = new GetAllAttendanceResponseDto();
                    response.setFirstName(result[4].toString());
                    response.setMiddleName(result[5].toString());
                    response.setLastName(result[6].toString());
                    response.setAttendanceDate(DateFormatUtils.format((Date)result[0],"yyyy-MM-dd"));
                    response.setStatus(result[2].toString());
                    response.setRemark(result[1].toString());
                    response.setStudentId(result[3].toString());
                    return response;
                }).toList();
    }

    public List<GetStudentAttendanceResponseDto> getStudentAttendance(Map<String, String> search, Pageable pageable, HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("userId");
        GetStudentAttendanceDto dto = new GetStudentAttendanceDto(search);

        Specification<Attendance> specification = AttendanceSpecifications.searchByStudentId(id);
        if(search.size()==0)
        {
            specification = specification.and(AttendanceSpecifications.getAllAttendance());
        }
        else{
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                specification = specification.and(AttendanceSpecifications.attendanceBetweenRange(dto.getStartDate(), dto.getEndDate()));
            } else if (dto.getStartDate() != null) {
                specification = specification.and(AttendanceSpecifications.todayAttendance(dto.getStartDate()));
            } else if (dto.getEndDate() != null) {
                specification = specification.and(AttendanceSpecifications.AllPast(dto.getEndDate()));
            }
        }

        List<Attendance> map = attendanceRepository.findAll(specification, pageable).getContent();

        return map.stream().map(result -> {
            GetStudentAttendanceResponseDto response = new GetStudentAttendanceResponseDto();
            response.setStatus(result.getStatus());
            response.setRemark(result.getRemark());
            response.setAttendanceDate(DateFormatUtils.format(result.getAttendanceDate(),"yyyy-MM-dd")); // Use result.getAttendanceDate() instead of response.getAttendanceDate()
            return response;
        }).toList();

    }


}
