package et.com.gebeya.askuala_comm.service;

import et.com.gebeya.askuala_comm.dto.LoginRequestDto;
import et.com.gebeya.askuala_comm.model.Users;
import et.com.gebeya.askuala_comm.repository.UsersRepository;
import et.com.gebeya.askuala_comm.repository.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginRequestDto dto) {
        Optional<Users> user = usersRepository.findFirstByUserName(dto.getUserName());
        if (user.isEmpty())
            return null;


        if (passwordEncoder.matches(dto.getPassword(),user.get().getPassword()))
            return user.get().getUserName();
        else return null;
    }


    public List<String> getAllStudent(){
        List<Users> users = usersRepository.findAll(UserSpecification.getAllStudent());
        return users.stream().map(Users::getUserName).toList();
    }

    public List<String> getAllTeacher(){
        List<Users> users = usersRepository.findAll(UserSpecification.getAllTeacher());
        return users.stream().map(Users::getUserName).toList();
    }

}
