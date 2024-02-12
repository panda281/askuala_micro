package et.com.gebeya.askuala_comm.telegram;

import et.com.gebeya.askuala_comm.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static et.com.gebeya.askuala_comm.telegram.Constants.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResponseHandler {
    private final TelegramService telegramService;
    private final RedisService redisService;
    private final KeyboardFactory keyboardFactory;

    public SendMessage replyForWelcome(Long chatId) {

        if (redisService.getUser("1") == null) System.out.println("user not found");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(WELCOME_MESSAGE);
        redisService.setUserState(chatId, UserState.START);
        return sendMessage;
    }

    public SendMessage replyForAlreadyLoginUser(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(SUCCESSFUL_LOGIN_MESSAGE);
        sendMessage.setReplyMarkup(keyboardFactory.replyForLoginKeyBoard());
        return sendMessage;
    }

    public SendMessage replyForLogin(Long chatId, String message) {
        String[] usernamePassword = message.split("\n");
        LoginRequestDto request = LoginRequestDto.builder().userName(usernamePassword[0]).password(usernamePassword[1]).build();
        Boolean response = telegramService.login(request);
        if (response.equals(false)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(UNSUCCESSFUL_LOGIN_MESSAGE);
            return sendMessage;
        } else {
            redisService.setUserState(chatId, UserState.LOGGED_IN);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(SUCCESSFUL_LOGIN_MESSAGE);
            sendMessage.setReplyMarkup(keyboardFactory.replyForLoginKeyBoard());
            return sendMessage;
        }


    }

    public SendMessage replyForType(Long chatId, String message) {
        if (!(message.equals("Student") || message.equals("Teacher"))) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(INCORRECT_INPUT_FOR_CHAT_TYPE);
            sendMessage.setReplyMarkup(keyboardFactory.replyForLoginKeyBoard());
            return sendMessage;
        } else {
            if (message.equals("Student")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(STUDENT_CHAT_TYPE);
                sendMessage.setReplyMarkup(keyboardFactory.replyForStudentSearchKeyBoard());
                redisService.setUserState(chatId, UserState.STUDENT_TYPE);
                return sendMessage;
            } else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(TEACHER_CHAT_TYPE);
                sendMessage.setReplyMarkup(keyboardFactory.replyForTeacherSearchKeyBoard());
                redisService.setUserState(chatId, UserState.TEACHER_TYPE);
                return sendMessage;
            }
        }
    }

    public SendMessage replyForStudentOrTeacherType(Long chatId, String message){
        if(message.equals("ListStudents")){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("list of students");
            redisService.setUserState(chatId,UserState.LIST_OF_STUDENT);
            return  sendMessage;
        } else if (message.equals("ListTeachers")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("list of teachers");
            redisService.setUserState(chatId,UserState.LIST_OF_TEACHER);
            return  sendMessage;
        }
        else{
            if(redisService.getUser(message)==null)
            {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(ID_NOTFOUND);
                sendMessage.setReplyMarkup(keyboardFactory.replyForLoginKeyBoard());
                redisService.setUserState(chatId,UserState.LOGGED_IN);
                return sendMessage;
            }
            else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(idFound(message));
                redisService.setUserState(chatId,UserState.MESSAGE);
                return sendMessage;
            }
        }
    }


}
