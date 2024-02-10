package et.com.gebeya.askuala_school_management_management_service.util;


import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.*;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.AttendanceResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GradeSectionResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.SubjectResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.TeacherSubjectResponse;
import et.com.gebeya.askuala_school_management_management_service.enums.Gender;
import et.com.gebeya.askuala_school_management_management_service.model.*;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Component
public class MappingUtil {
    public static Student mapStudentDtoToModel(StudentRequestDto dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setMiddleName(dto.getMiddleName());
        student.setLastName(dto.getLastName());
        student.setDob(dto.getDob());
        student.setStudentId(dto.getStudentId());
        student.setGender(dto.getGender());
        student.setIsActive(true);

        Address address = mapAddressDtoToModel(dto.getAddressDto());
        student.setAddress(address);

        return student;
    }

    public static  Set<Guardian> mapGuardianDtoToModel(Set<GuardianDto> dto)
    {
        Set<Guardian> guardians = dto
                .stream()
                .map(MappingUtil::mapSingleGuardianDtoToModel)
                .collect(Collectors.toSet());

        return guardians;
    }

    private static Address mapAddressDtoToModel(AddressDto dto) {
        Address address = new Address();
        address.setEmail(dto.getEmail());
        address.setCity(dto.getCity());
        address.setSubCity(dto.getSubCity());
        address.setWereda(dto.getWereda());
        address.setHouseNumber(dto.getHouseNumber());
        address.setIsActive(true);

        List<PhoneNumber> phoneNumbers = dto.getPhoneNumber()
                .stream()
                .map(MappingUtil::mapPhoneNumberDtoToModel)
                .collect(Collectors.toList());
        address.setPhoneNumber(phoneNumbers);

        return address;
    }

    public static Guardian mapSingleGuardianDtoToModel(GuardianDto dto) {
        return getGuardian(dto);
    }

    public static Guardian UpdateGuardian(GuardianDto dto) {
        return getGuardian(dto);
    }


    private static Guardian getGuardian(GuardianDto dto) {
        Guardian guardian = new Guardian();
        guardian.setFirstName(dto.getFirstName());
        guardian.setMiddleName(dto.getMiddleName());
        guardian.setLastName(dto.getLastName());
        guardian.setGender(dto.getGender());
        guardian.setIsActive(true);
        Address address = mapAddressDtoToModel(dto.getAddressDto());
        guardian.setAddress(address);

        return guardian;
    }

    private static PhoneNumber mapPhoneNumberDtoToModel(PhoneNumberDto dto) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber(dto.getPhoneNumber());
        phoneNumber.setIsActive(true);
        return phoneNumber;
    }

    private static GradeSection mapGradeSectionRequestDtoToGradeSection(GradeSectionDto gradeSectionDto) {
        GradeSection gradeSection = new GradeSection();
        gradeSection.setGrade(gradeSectionDto.getGrade());
        gradeSection.setSection(gradeSectionDto.getSection());
        gradeSection.setIsActive(true);
//        if(teacher)
//        gradeSection.setHomeRoomTeacher();
        return gradeSection;
    }

    public static Subject mapSubjectRequestDtoToSubject(SubjectRequestDto subjectRequestDto) {
        Subject subject = new Subject();
        subject.setName(subjectRequestDto.getName());
        subject.setIsActive(true);
        return subject;
    }

    public static Teacher mapTeacherRequestDtoToTeacher(TeacherRequestDto dto) {
        Teacher teacher = new Teacher();
        teacher.setQualifications(dto.getQualifications());
        teacher.setFirstName(dto.getFirstName());
        teacher.setMiddleName(dto.getMiddleName());
        teacher.setLastName(dto.getLastName());
        teacher.setGender(dto.getGender());
        teacher.setIsActive(true);
        Address address = mapAddressDtoToModel(dto.getAddressDto());
        teacher.setAddress(address);


        return teacher;
    }

    public static GradeSection mapGradeSectionRequestDtoToGradeSection(GradeSectionRequestDto gradeSectionDto) {
        GradeSection gradeSection = new GradeSection();
        gradeSection.setGrade(gradeSectionDto.getGrade());
        gradeSection.setSection(gradeSectionDto.getSection());
        gradeSection.setIsActive(true);
        return gradeSection;
    }


    public static SubjectResponseDto mapSubjectToSubjectResponseDto(Subject subjects){
        SubjectResponseDto dto = new SubjectResponseDto();
        dto.setId(subjects.getId());
        dto.setName(subjects.getName());
        return dto;
    }

    public static Attendance AttendanceDtoToModel(AttendanceRequestDto attendanceDto)
    {
        Attendance attendance = new Attendance();
        attendance.setStatus(attendanceDto.getStatus());
        attendance.setRemark(attendanceDto.getRemark());
        attendance.setAttendanceDate(attendanceDto.getAttendanceDate());
        attendance.setIsActive(true);
        return attendance;
    }

    public static TeacherSubjectResponse TeacherSubjectToDto(TeacherSubject teacherSubject)
    {
        TeacherSubjectResponse teacherSubjectResponse = new TeacherSubjectResponse();
        teacherSubjectResponse.setName(teacherSubject.getSubject().getName());
        return teacherSubjectResponse;
    }

    public static GradeSectionResponseDto GradeSectionToDto(GradeSection gradeSection)
    {
        assert gradeSection.getHomeRoomTeacher() != null;
        Teacher homeRoomTeacher = gradeSection.getHomeRoomTeacher();
        String homeRoomTeacherName = null;
        Gender gender = null;

        if (homeRoomTeacher != null) {
            homeRoomTeacherName = homeRoomTeacher.getFirstName()
                    + " " + homeRoomTeacher.getMiddleName()
                    + " " + homeRoomTeacher.getLastName();
            gender = homeRoomTeacher.getGender();
        }

        return GradeSectionResponseDto.builder()
                .id(gradeSection.getId())
                .homeRoomTeacherName(homeRoomTeacherName)
                .gender(gender)
                .section(gradeSection.getSection())
                .grade(gradeSection.getGrade())
                .build();
    }

    public static AttendanceResponseDto AttendanceToAttendanceResponseDto(Attendance attendance)
    {
        AttendanceResponseDto attendanceResponseDto = new AttendanceResponseDto();
        attendanceResponseDto.setId(attendance.getId());
        attendanceResponseDto.setTeacherId(attendance.getTeacher().getId());
        attendanceResponseDto.setStudentId(attendance.getStudent().getId());
        attendanceResponseDto.setStatus(attendance.getStatus());
        attendanceResponseDto.setRemark(attendance.getRemark());
        attendanceResponseDto.setAttendanceDate(DateFormatUtils.format(attendance.getAttendanceDate(),"yyyy-MM-dd"));
        return attendanceResponseDto;
    }

    public static Admin mapAdminDtoToModel(AdminRequestDto dto) {
       Admin admin = new Admin();
        admin.setFirstName(dto.getFirstName());
        admin.setMiddleName(dto.getMiddleName());
        admin.setLastName(dto.getLastName());
        admin.setIsActive(true);
        admin.setGender(dto.getGender());
        Address address = mapAddressDtoToModel(dto.getAddressDto());
        admin.setAddress(address);
        return admin;
    }

}

