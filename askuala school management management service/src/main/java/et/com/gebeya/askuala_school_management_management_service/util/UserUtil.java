package et.com.gebeya.askuala_school_management_management_service.util;

import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.AddAccountRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.requestdto.PaymentRequestDto;
import et.com.gebeya.askuala_school_management_management_service.dto.responsedto.UserDto;
import et.com.gebeya.askuala_school_management_management_service.enums.Authority;
import et.com.gebeya.askuala_school_management_management_service.model.Users;
import et.com.gebeya.askuala_school_management_management_service.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

import static et.com.gebeya.askuala_school_management_management_service.util.Constant.ADD_ACCOUNT_TOPIC;

@Component
@RequiredArgsConstructor
public class UserUtil {
   private final UsersRepository usersRepository;
   private final PasswordEncoder passwordEncoder;
    private final KafkaTemplate<String, AddAccountRequestDto> addAccountRequestDtoKafkaTemplate;
    private static StringBuilder randomStringGenerator() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        final int DEFAULT_LENGTH = 12;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            stringBuilder.append(randomChar);
        }
        return stringBuilder;
    }

    public void createUser(String fName, String mName, Integer id, Authority authority, String email) {
        String username = fName.toLowerCase() + "." + mName.toLowerCase().charAt(0);
        String password = String.valueOf(randomStringGenerator());
        Users users = Users.builder()
                .userName(username)
                .password(passwordEncoder.encode(password))
                .authority(authority)
                .isActive(true)
                .roleId(id)
                .build();

        usersRepository.save(users);


        AddAccountRequestDto request = AddAccountRequestDto.builder().name(fName).email(email).username(username).password(password).build();
        addAccountRequestDtoKafkaTemplate.send(ADD_ACCOUNT_TOPIC,request);
    }

    public void userDisabler(Users users)
    {

        users.setIsActive(false);
        usersRepository.save(users);
    }
}
