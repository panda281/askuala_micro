package et.com.gebeya.askuala_school_management_management_service.service;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.GradeSectionRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.HomeRoomTeacherDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.SearchGradeSectionRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GradeSectionResponse;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GradeSectionResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.StudentGradeSectionResponse;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.TeacherGradeSectionResponse;
import et.com.gebeya.askuala_school_management_management_service.enums.Grade;
import et.com.gebeya.askuala_school_management_management_service.enums.Section;
import et.com.gebeya.askuala_school_management_management_service.exception.ErrorHandler;
import et.com.gebeya.askuala_school_management_management_service.model.GradeSection;
import et.com.gebeya.askuala_school_management_management_service.model.Subject;
import et.com.gebeya.askuala_school_management_management_service.model.Teacher;
import et.com.gebeya.askuala_school_management_management_service.repository.GradeSectionRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.SubjectRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.TeacherRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.specifications.GradeSectionSpecifications;
import et.com.gebeya.askuala_school_management_management_service.util.MappingUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeSectionService {
    private final GradeSectionRepository gradeSectionRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    public GradeSectionResponse addGradeSection(GradeSectionRequestDto dto){

        if(isGradeSectionExist(dto.getGrade(), dto.getSection()))
            return new GradeSectionResponse();

        GradeSection gradeSection = MappingUtil.mapGradeSectionRequestDtoToGradeSection(dto);
        Set<Subject> subject= getSubjectById(dto.getSubjectId());
        gradeSection.setSubjects(subject);

        gradeSection = gradeSectionRepository.save(gradeSection);
        return GradeSectionResponse.builder().id(gradeSection.getId())
                .section(gradeSection.getSection())
                .grade(gradeSection.getGrade())
                .subjectList(gradeSection.getSubjects())
                .build();
    }

    private Set<Subject> getSubjectById(Set<Integer> id)
    {
        try {
            return subjectRepository.findAllByIdIn(id);
        }
        catch (Exception e) {
            throw new ErrorHandler(HttpStatus.NOT_FOUND,e.getMessage());
        }

    }

    private boolean isGradeSectionExist(Grade grade, Section section)
    {
        return gradeSectionRepository.exists(GradeSectionSpecifications
                .getGradeSectionByGradeAndSection(grade,section));
    }

    public GradeSectionResponse updateGradeSection(Integer id,GradeSectionRequestDto dto)
    {
        GradeSection gradeSection = gradeSectionRepository.findById(id)
                .orElseThrow(()->new ErrorHandler(HttpStatus.NOT_FOUND,id+" can't be found"));
        Set<Subject> subject= getSubjectById(dto.getSubjectId());
        gradeSection.setSubjects(subject);
        gradeSection.setSection(dto.getSection());
        gradeSection.setGrade(dto.getGrade());
        gradeSection = gradeSectionRepository.save(gradeSection);
        return GradeSectionResponse.builder().id(gradeSection.getId())
                .section(gradeSection.getSection())
                .grade(gradeSection.getGrade())
                .subjectList(gradeSection.getSubjects())
                .build();

    }

    public GradeSection deleteGradeSectionId(Integer id)
    {
        GradeSection gradeSection = gradeSectionRepository.findById(id).orElseThrow(()->new ErrorHandler(HttpStatus.NOT_FOUND, id+ " can't be found"));
        gradeSection.setIsActive(false);
        return gradeSectionRepository.save(gradeSection);
    }

    public GradeSectionResponseDto findByGradeSectionId(Integer id)
    {
        GradeSection gradeSection =  gradeSectionRepository.findById(id).orElseThrow(()->new ErrorHandler(HttpStatus.NOT_FOUND,id+ " can't be found"));
        assert gradeSection.getHomeRoomTeacher() != null;
        return MappingUtil.GradeSectionToDto(gradeSection);
    }

    public List<GradeSectionResponseDto> getAllGradeSection(Pageable pageable){
        List<GradeSection> gradeSection =  gradeSectionRepository.findAll(GradeSectionSpecifications.getAllGradeSection(),pageable).getContent();
        return gradeSection.stream().map(MappingUtil::GradeSectionToDto).toList();
    }

    public List<GradeSection> searchGradeSection(Map<String,String> request, Pageable pageable)
    {
        SearchGradeSectionRequestDto dto = new SearchGradeSectionRequestDto(request);
        Specification<GradeSection> specification = GradeSectionSpecifications.getAllGradeSection();
        if(dto.getTeacherId()!=0)
            specification = specification.and(GradeSectionSpecifications.GradeSectionByTeacherId(dto.getTeacherId()));
        else if (dto.getTeacherFirstName()!=null) {
            specification = specification.and(GradeSectionSpecifications.GradeSectionByTeacherFirstName(dto.getTeacherFirstName()));
        }
        else if (dto.getGrade()!=null){
            specification = specification.and(GradeSectionSpecifications.getGradeSectionByGrade(dto.getGrade()));
        }
        return gradeSectionRepository.findAll(specification,pageable).getContent();
    }

    public GradeSectionResponseDto addHomeRoomTeacher(HomeRoomTeacherDto dto)
    {
        GradeSection gradeSection = gradeSectionRepository.findById(dto.getGradeSectionId())
                .orElseThrow(()->(new ErrorHandler(HttpStatus.NOT_FOUND,dto.getGradeSectionId()+ " can't be found")));
        Teacher teacher = teacherRepository.findById(dto.getTeacherId()).orElseThrow(
                ()->new ErrorHandler(HttpStatus.NOT_FOUND,dto.getTeacherId()+ " can't be found")
        );
        gradeSection.setHomeRoomTeacher(teacher);
        gradeSection = gradeSectionRepository.save(gradeSection);
        return MappingUtil.GradeSectionToDto(gradeSection);
    }

    public List<TeacherGradeSectionResponse> getTeacherGradeSection(HttpServletRequest request)
    {
        Integer id = (Integer) request.getAttribute("userId");
        List<Object[]> map =  gradeSectionRepository.findGradeSectionByTeacherId(id);
        return map.stream()
                .map(result -> {
                    TeacherGradeSectionResponse response = new TeacherGradeSectionResponse();
                    response.setSection(Section.valueOf((String) result[1]));
                    response.setGrade(Grade.valueOf((String) result[0]));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<StudentGradeSectionResponse> getStudentGradeSection(HttpServletRequest request)
    {
        Integer id = (Integer) request.getAttribute("userId");
        List<Object[]> map =  gradeSectionRepository.findGradeSectionByStudentId(id);
        return map.stream()
                .map(result -> {
                    StudentGradeSectionResponse response = new StudentGradeSectionResponse();
                    response.setSection(Section.valueOf((String) result[1]));
                    response.setGrade(Grade.valueOf((String) result[0]));
                    return response;
                })
                .collect(Collectors.toList());
    }
}
