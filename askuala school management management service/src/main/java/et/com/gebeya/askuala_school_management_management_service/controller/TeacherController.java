package et.com.gebeya.askuala_school_management_management_service.controller;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.TeacherRequestDto;
import et.com.gebeya.askuala_school_management_management_service.model.Teacher;
import et.com.gebeya.askuala_school_management_management_service.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/school/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/teachers")
    public ResponseEntity<TeacherRequestDto> addTeacher(@RequestBody TeacherRequestDto teacherRequestDto)
    {
        return ResponseEntity.ok(teacherService.TeacherRegistration(teacherRequestDto));
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getTeacher(@PageableDefault(page = 0, size = 10) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(teacherService.getAllTeachers(pageable));
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher>getTeacherById(@PathVariable("id")Integer id)
    {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }


    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeachers(@PathVariable("id") int id, @RequestBody TeacherRequestDto teacherRequestDto){
        return ResponseEntity.ok(teacherService.updateTeacher(id,teacherRequestDto));
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Teacher> deleteTeachers(@PathVariable("id") int id){
        return ResponseEntity.ok(teacherService.deleteTeacher(id));
    }
}
