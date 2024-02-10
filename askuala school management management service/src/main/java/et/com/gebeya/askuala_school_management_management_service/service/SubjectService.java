package et.com.gebeya.askuala_school_management_management_service.service;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.SubjectRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.StudentSubjectResponse;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.SubjectResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.TeacherSubjectResponse;
import et.com.gebeya.askuala_school_management_management_service.exception.ErrorHandler;
import et.com.gebeya.askuala_school_management_management_service.model.Subject;
import et.com.gebeya.askuala_school_management_management_service.repository.SubjectRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.specifications.SubjectSpecifications;
import et.com.gebeya.askuala_school_management_management_service.util.MappingUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    public SubjectResponseDto addSubject(SubjectRequestDto dto){
        Optional<Subject> student = subjectRepository.findOne(SubjectSpecifications.getByName(dto.getName()));
        if(student.isEmpty())
        {
            Subject subject = subjectRepository.save(MappingUtil.mapSubjectRequestDtoToSubject(dto));
            return SubjectResponseDto.builder().id(subject.getId()).name(subject.getName()).build();
        }
        return new SubjectResponseDto();

    }


    public SubjectResponseDto updateSubject(Integer id,SubjectRequestDto dto)
    {
        Subject subject = subjectRepository.findById(id).orElseThrow(()-> new ErrorHandler(HttpStatus.NOT_FOUND,id + "can't be found"));
        subject.setName(dto.getName());
        subject = subjectRepository.save(subject);
        return SubjectResponseDto.builder().name(subject.getName()).id(subject.getId()).build();
    }

    public List<SubjectResponseDto> getAllSubjects(){

        List<Subject> subjects = subjectRepository.findAll(SubjectSpecifications.getAllSubject());
        return subjects.stream().map(MappingUtil::mapSubjectToSubjectResponseDto).toList();
    }
    public Subject deleteSubject(Integer id)
    {
        Subject subject = subjectRepository.findById(id).orElseThrow(()->new RuntimeException(id + " can't be found"));
        subject.setIsActive(false);
        return subjectRepository.save(subject);
    }

    public List<TeacherSubjectResponse> getTeacherSubject(HttpServletRequest request)
    {
        Integer id = (Integer) request.getAttribute("userId");
        List<Object[]> map =  subjectRepository.getTeacherSubject(id);
        return map.stream()
                .map(result -> {
                    TeacherSubjectResponse response = new TeacherSubjectResponse();
                    response.setName((String) result[0]);
                    return response;
                })
                .collect(Collectors.toList());
    }
    public List<StudentSubjectResponse> getStudentSubject(HttpServletRequest request)
    {
        Integer id = (Integer) request.getAttribute("userId");
        List<Object[]> map =  subjectRepository.getTeacherSubject(id);
        return map.stream()
                .map(result -> {
                    StudentSubjectResponse response = new StudentSubjectResponse();
                    response.setName((String) result[0]);
                    return response;
                })
                .collect(Collectors.toList());
    }

}
