package et.com.gebeya.askuala_school_management_management_service.service;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.TeacherRequestDto;
import et.com.gebeya.askuala_school_management_management_service.enums.Authority;
import et.com.gebeya.askuala_school_management_management_service.exception.ErrorHandler;
import et.com.gebeya.askuala_school_management_management_service.model.*;
import et.com.gebeya.askuala_school_management_management_service.repository.*;
import et.com.gebeya.askuala_school_management_management_service.repository.specifications.TeacherSpecifications;
import et.com.gebeya.askuala_school_management_management_service.util.MappingUtil;
import et.com.gebeya.askuala_school_management_management_service.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserUtil userUtil;
    private final SubjectRepository subjectRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final GradeSectionRepository gradeSectionRepository;
    private final UsersRepository usersRepository;
    @Transactional
    public TeacherRequestDto TeacherRegistration(TeacherRequestDto teacherRequestDto)
    {

        Teacher teacher = MappingUtil.mapTeacherRequestDtoToTeacher(teacherRequestDto);
        Set<Subject> subjects = getSubjectById(teacherRequestDto.getSubject());
        Set<GradeSection> gradeSections = getGradeSectionById(teacherRequestDto.getGradeSection());
        teacher = teacherRepository.save(teacher);
        teacherSubjectRepository.saveAll(teacherSubjectSave(subjects,gradeSections,teacher));
        userUtil.createUser(teacher.getFirstName(),teacher.getMiddleName(),teacher.getId(), Authority.TEACHER, teacherRequestDto.getAddressDto().getEmail());
        return teacherRequestDto;
    }

    private Set<TeacherSubject> teacherSubjectSave(Set<Subject> subjects, Set<GradeSection> gradeSections, Teacher teacher)
    {
        Set<TeacherSubject> teacherSubjects = new HashSet<>();

        for (Subject subject : subjects) {
            for (GradeSection gradeSection : gradeSections) {
                TeacherSubjectId teacherSubjectId = new TeacherSubjectId(teacher.getId(), subject.getId(), gradeSection.getId());
                TeacherSubject teacherSubject = new TeacherSubject();
                teacherSubject.setId(teacherSubjectId);
                teacherSubject.setTeacher(teacher);
                teacherSubject.setSubject(subject);
                teacherSubject.setGradeSection(gradeSection);
                teacherSubjects.add(teacherSubject);
            }
        }

        return teacherSubjects;
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

    private Set<GradeSection> getGradeSectionById(Set<Integer> id){

        try{
            return gradeSectionRepository.findAllByIdIn(id);
        }
        catch (Exception e)
        {
            throw new ErrorHandler(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    public Teacher updateTeacher(Integer id,TeacherRequestDto teacherRequestDto)
    {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()->new ErrorHandler(HttpStatus.NOT_FOUND,id+"can't be found"));
        teacher.setGender(teacherRequestDto.getGender());
        teacher.setFirstName(teacherRequestDto.getFirstName());
        teacher.setLastName(teacherRequestDto.getLastName());
        teacher.setMiddleName(teacherRequestDto.getMiddleName());
        teacher.setQualifications(teacherRequestDto.getQualifications());
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers(Pageable pageable)
    {
        return teacherRepository.findAll(TeacherSpecifications.getAllTeachers(),pageable).getContent();
    }

    public Teacher getTeacherById(Integer id)
    {
        return teacherRepository.findOne(TeacherSpecifications.getAllTeachers().and(TeacherSpecifications.getTeacherById(id)))
                .orElseThrow(()->new ErrorHandler(HttpStatus.NOT_FOUND,id + " can't be found"));
    }


    public Teacher deleteTeacher(Integer id)
    {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()->new RuntimeException(id + " can't be found"));
        teacher.setIsActive(false);
        teacher = teacherRepository.save(teacher);
        Users users = usersRepository.findByRoleId(teacher.getId()).orElseThrow(()->new ErrorHandler(HttpStatus.NOT_FOUND,"id can't be found"));

        teacher= teacherRepository.save(teacher);
        userUtil.userDisabler(users);
        return teacher;

    }

}
