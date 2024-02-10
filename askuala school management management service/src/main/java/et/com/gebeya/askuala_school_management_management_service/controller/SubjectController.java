package et.com.gebeya.askuala_school_management_management_service.controller;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.SubjectRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.StudentSubjectResponse;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.SubjectResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.TeacherSubjectResponse;
import et.com.gebeya.askuala_school_management_management_service.model.Subject;
import et.com.gebeya.askuala_school_management_management_service.service.SubjectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/subjects")
    public ResponseEntity<SubjectResponseDto> addSubject(@RequestBody SubjectRequestDto subjectRequestDto)
    {
        return ResponseEntity.ok(subjectService.addSubject(subjectRequestDto));
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectResponseDto>> getSubjects()
    {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }


    @PutMapping("/subjects/{id}")
    public ResponseEntity<SubjectResponseDto> updateSubjects(@PathVariable("id") int id, @RequestBody SubjectRequestDto subjectRequestDto)
    {
        return ResponseEntity.ok(subjectService.updateSubject(id,subjectRequestDto));
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<Subject> deleteSubjects(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(subjectService.deleteSubject(id));
    }
    @GetMapping("/teacher/subject")
    public ResponseEntity<List<TeacherSubjectResponse>> getTeacherSubjects(HttpServletRequest request){
        return ResponseEntity.ok(subjectService.getTeacherSubject(request));
    }
    @GetMapping("/student/subject")
    public ResponseEntity<List<StudentSubjectResponse>> getStudentSubjects(HttpServletRequest request){
        return ResponseEntity.ok(subjectService.getStudentSubject(request));
    }

}
