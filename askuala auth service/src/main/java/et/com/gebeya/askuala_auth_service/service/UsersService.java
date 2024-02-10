package et.com.gebeya.askuala_auth_service.service;



import et.com.gebeya.askuala_auth_service.model.Users;
import et.com.gebeya.askuala_auth_service.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    public UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }

    public Users createUpdateUser(Users users) {
        return usersRepository.save(users);
    }


@Cacheable(value = "user", key = "#userName")
    public Users loadUserByUsername(String userName) {
        return usersRepository.findFirstByUserName(userName).orElseThrow(() -> new IllegalArgumentException("Invalid user name or password"));

    }


}
