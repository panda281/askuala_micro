package et.com.gebeya.askuala_school_management_management_service.controller;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.AdminRequestDto;
import et.com.gebeya.askuala_school_management_management_service.model.Admin;
import et.com.gebeya.askuala_school_management_management_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admins")
    public ResponseEntity<AdminRequestDto> addAdmin(AdminRequestDto dto) {
        return ResponseEntity.ok(adminService.addAdmin(dto));
    }


    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") int id) {
        return ResponseEntity.ok(adminService.deleteAdmin(id));
    }

    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAdmins() {
        return ResponseEntity.ok(adminService.getAdmins());
    }
}
