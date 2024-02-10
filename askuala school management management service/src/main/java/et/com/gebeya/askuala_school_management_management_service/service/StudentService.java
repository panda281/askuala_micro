package et.com.gebeya.askuala_school_management_management_service.service;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.GuardianDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.SearchStudentDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.StudentRequestDto;
import et.com.gebeya.askuala_school_management_management_service.enums.Authority;
import et.com.gebeya.askuala_school_management_management_service.exception.ErrorHandler;
import et.com.gebeya.askuala_school_management_management_service.model.GradeSection;
import et.com.gebeya.askuala_school_management_management_service.model.Guardian;
import et.com.gebeya.askuala_school_management_management_service.model.Student;
import et.com.gebeya.askuala_school_management_management_service.model.Users;
import et.com.gebeya.askuala_school_management_management_service.repository.*;
import et.com.gebeya.askuala_school_management_management_service.repository.specifications.GradeSectionSpecifications;
import et.com.gebeya.askuala_school_management_management_service.repository.specifications.GuardianSpecifications;
import et.com.gebeya.askuala_school_management_management_service.repository.specifications.StudentSpecifications;
import et.com.gebeya.askuala_school_management_management_service.util.MappingUtil;
import et.com.gebeya.askuala_school_management_management_service.util.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final SubjectRepository subjectRepository;
    private final GradeSectionRepository gradeSectionRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final StudentRepository studentRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final GuardianRepository guardianRepository;
    private final AttendanceRepository attendanceRepository;
    private final AdminRepository adminRepository;
    private final UserUtil userUtil;


    private Boolean isGuardianExists(String fName, String mName, String lName) {
        return guardianRepository.exists(GuardianSpecifications
                .getGuardianByFirstMiddleLastName(fName, mName, lName).and(GuardianSpecifications.getAllGuardians()));
    }


    @Transactional
    public StudentRequestDto addStudent(StudentRequestDto dto) {
        Set<GuardianDto> guardianDto = dto.getGuardian();
        Set<Guardian> guardians = new HashSet<>();


        for (GuardianDto guardianNewDto : guardianDto) {
            Guardian guardian;
            if (isGuardianExists(guardianNewDto.getFirstName(), guardianNewDto.getMiddleName(), guardianNewDto.getLastName())) {
                guardian = guardianRepository.findOne(GuardianSpecifications.getGuardianByFirstMiddleLastName(
                        guardianNewDto.getFirstName(), guardianNewDto.getMiddleName(), guardianNewDto.getLastName()
                )).orElseThrow(() -> (new ErrorHandler(HttpStatus.NOT_FOUND, "unable to get guardian")));
            } else {
                guardian = guardianRepository.save(MappingUtil.mapSingleGuardianDtoToModel(guardianNewDto));
            }
            guardians.add(guardian);
        }

        Student student = MappingUtil.mapStudentDtoToModel(dto);
        student.setGuardian(guardians);
        GradeSection gradeSection = gradeSectionRepository.findOne(GradeSectionSpecifications
                .getGradeSectionByGradeAndSection(dto.getGrade(), dto.getSection())).orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "gradeSection could not be found"));
        student.setGradeSection(gradeSection);
        student = studentRepository.save(student);
        userUtil.createUser(student.getFirstName(), student.getMiddleName(), student.getId(), Authority.STUDENT, student.getAddress().getEmail());
        return dto;
    }


    public Student updateStudent(String id, StudentRequestDto dto) {
        Student existingStudent = findByStudentId(id);
        existingStudent.setFirstName(dto.getFirstName());
        existingStudent.setMiddleName(dto.getMiddleName());
        existingStudent.setLastName(dto.getLastName());
        existingStudent.setStudentId(dto.getStudentId());
        existingStudent.setDob(dto.getDob());

        GradeSection gradeSection = gradeSectionRepository.findOne(GradeSectionSpecifications.getGradeSectionByGradeAndSection(dto.getGrade(), dto.getSection()))
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, id + "can't be found"));
        existingStudent.setGradeSection(gradeSection);

        return studentRepository.save(existingStudent);
    }

    public Student deleteStudent(String id) {
        Student student = studentRepository.findByStudentId(id).orElseThrow(() -> new RuntimeException(id + " can't not be found"));
        student.setIsActive(false);
        Users user = usersRepository.findByRoleId(student.getId()).orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, " id can't be found"));
        userUtil.userDisabler(user);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(StudentSpecifications.getAllStudents(), pageable).getContent();
    }

    public Student findByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId).orElseThrow(() ->
                new ErrorHandler(HttpStatus.NOT_FOUND, studentId + " can't be found"));
    }

    public List<Student> searchStudent(Map<String, String> request, Pageable pageable) {
        SearchStudentDto dto = new SearchStudentDto(request);
        Specification<Student> specification = StudentSpecifications.getAllStudents();
        if (dto.getFirstName() != null) {

            specification = specification.and(StudentSpecifications.StudentByName(dto.getFirstName()));

        } else if (dto.getGrade() != null && dto.getSection() != null) {
            specification = specification.and(StudentSpecifications.StudentByGradeSection(dto.getGrade(), dto.getSection()));
        }

        return studentRepository.findAll(specification, pageable).getContent();
    }

}
