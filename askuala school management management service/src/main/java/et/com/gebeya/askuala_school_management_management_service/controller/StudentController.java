package et.com.gebeya.askuala_school_management_management_service.controller;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.StudentRequestDto;
import et.com.gebeya.askuala_school_management_management_service.model.Student;
import et.com.gebeya.askuala_school_management_management_service.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<StudentRequestDto> addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        return ResponseEntity.ok(studentService.addStudent(studentRequestDto));
    }


    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody StudentRequestDto student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") String id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }


    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(@PageableDefault(page = 0, size = 10) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }


    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(studentService.findByStudentId(id));
    }


    @GetMapping("/students/search")
    public ResponseEntity<?> getStudentByParam(@PageableDefault(page = 0, size = 10) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                               @RequestParam(required = false) Map<String, String> search) {
        return ResponseEntity.ok(studentService.searchStudent(search, pageable));
    }
}
