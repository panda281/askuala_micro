package et.com.gebeya.askuala_school_management_management_service.service;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.AdminRequestDto;
import et.com.gebeya.askuala_school_management_management_service.enums.Authority;
import et.com.gebeya.askuala_school_management_management_service.exception.ErrorHandler;
import et.com.gebeya.askuala_school_management_management_service.model.Admin;
import et.com.gebeya.askuala_school_management_management_service.model.Users;
import et.com.gebeya.askuala_school_management_management_service.repository.AdminRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.UsersRepository;
import et.com.gebeya.askuala_school_management_management_service.repository.specifications.AdminSpecifications;
import et.com.gebeya.askuala_school_management_management_service.util.MappingUtil;
import et.com.gebeya.askuala_school_management_management_service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final UsersRepository usersRepository;
    private final UserUtil userUtil;

    public AdminRequestDto addAdmin(AdminRequestDto dto) {
        Admin admin = adminRepository.save(MappingUtil.mapAdminDtoToModel(dto));
        userUtil.createUser(admin.getFirstName(), admin.getMiddleName(), admin.getId(), Authority.ADMIN, admin.getAddress().getEmail());
        return dto;
    }

    public Admin deleteAdmin(Integer id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, id + " can't be found"));
        admin.setIsActive(false);
        admin = adminRepository.save(admin);
        Users user = usersRepository.findByRoleId(admin.getId()).orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND,
                id + " can't not be found"));
        userDisabler(user);
        return admin;
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll(AdminSpecifications.getAllAdmin());
    }

    public void userDisabler(Users users) {

        users.setIsActive(false);
        usersRepository.save(users);
    }




}
