package et.com.gebeya.askuala_school_management_management_service.controller;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.GradeSectionRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.HomeRoomTeacherDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GradeSectionResponse;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.GradeSectionResponseDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.StudentGradeSectionResponse;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.TeacherGradeSectionResponse;
import et.com.gebeya.askuala_school_management_management_service.model.GradeSection;
import et.com.gebeya.askuala_school_management_management_service.service.GradeSectionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/school/grade-section")
@RequiredArgsConstructor
public class GradeSectionController {
    private final GradeSectionService gradeSectionService;

    @PostMapping("/grade-sections")
    public ResponseEntity<GradeSectionResponse> addGradeSection(@RequestBody GradeSectionRequestDto gradeSectionDto) {
        return ResponseEntity.ok(gradeSectionService.addGradeSection(gradeSectionDto));
    }


    @PutMapping("/grade-sections/{id}")
    public ResponseEntity<GradeSectionResponse> updateGradeSection(@PathVariable("id") int id, @RequestBody GradeSectionRequestDto gradeSectionDto) {
        return ResponseEntity.ok(gradeSectionService.updateGradeSection(id, gradeSectionDto));
    }


    @DeleteMapping("/grade-sections/{id}")
    public ResponseEntity<GradeSection> deleteGradeSection(@PathVariable("id") int id) {
        return ResponseEntity.ok(gradeSectionService.deleteGradeSectionId(id));
    }

    @GetMapping("/grade-sections/{id}")
    public ResponseEntity<GradeSectionResponseDto> getGradeSectionById(@PathVariable("id") int id) {
        return ResponseEntity.ok(gradeSectionService.findByGradeSectionId(id));
    }

    @GetMapping("/grade-sections")
    public ResponseEntity<List<GradeSectionResponseDto>> getAllGradeSection(@PageableDefault(page = 0, size = 10) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(gradeSectionService.getAllGradeSection(pageable));
    }


    @GetMapping("/grade-sections/search")
    public ResponseEntity<List<GradeSection>> getGradeSectionWithParam(@PageableDefault(page = 0, size = 10) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                      @RequestParam(required = false) Map<String, String> search) {
        return ResponseEntity.ok(gradeSectionService.searchGradeSection(search, pageable));
    }
    @PostMapping("/teacher/grade-section")
    public ResponseEntity<GradeSectionResponseDto> addHomeRoomTeacher(@RequestBody HomeRoomTeacherDto dto)
    {
        return ResponseEntity.ok(gradeSectionService.addHomeRoomTeacher(dto));
    }

    @GetMapping("/grade-section/teacher")
    public ResponseEntity<List<TeacherGradeSectionResponse>> getTeacherGrade(HttpServletRequest request){
        return ResponseEntity.ok(gradeSectionService.getTeacherGradeSection(request));
    }

    @GetMapping("/grade-section/student")
    public ResponseEntity<List<StudentGradeSectionResponse>> getStudentGrade(HttpServletRequest request){
        return ResponseEntity.ok(gradeSectionService.getStudentGradeSection(request));
    }

}
