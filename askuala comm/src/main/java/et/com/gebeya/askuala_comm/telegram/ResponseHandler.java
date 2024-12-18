package et.com.gebeya.askuala_comm.telegram;

import et.com.gebeya.askuala_comm.dto.LoginRequestDto;
import et.com.gebeya.askuala_comm.dto.UserDto;
import et.com.gebeya.askuala_comm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import java.util.List;

import static et.com.gebeya.askuala_comm.telegram.Constants.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResponseHandler {
    private final UserService userService;
    private final RedisService redisService;
    private final KeyboardFactory keyboardFactory;

    public SendMessage replyForWelcome(Long chatId) {

//        if (redisService.getUser("1") == null) System.out.println("user not found");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(WELCOME_MESSAGE);
//        redisService.setState(chatId,UserState.START);
        redisService.setCache(chatId,UserDto.builder().state(UserState.START).username("").receiverChatId(0L).build());
        return sendMessage;
    }

    public SendMessage replyForAlreadyLoginUser(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(SUCCESSFUL_LOGIN_MESSAGE);
        sendMessage.setReplyMarkup(keyboardFactory.replyForLoginKeyBoard());
        //todo
        redisService.setState(chatId,UserState.LOGGEDIN);
        return sendMessage;
    }

    public SendMessage replyForLogin(Long chatId, String message) {
        String[] usernamePassword = message.split("\n");
        LoginRequestDto request = LoginRequestDto.builder().userName(usernamePassword[0]).password(usernamePassword[1]).build();
        String response = userService.login(request);
        if (response == null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(UNSUCCESSFUL_LOGIN_MESSAGE);
            return sendMessage;
        } else {
//            redisService.setCache(chatId, UserDto.builder().chatId(0L).username(usernamePassword[0]).build());
            redisService.setUser(usernamePassword[0],chatId);
            redisService.setCache(chatId,UserDto.builder().state(UserState.LOGGEDIN).username(usernamePassword[0]).build());
//            System.out.println(redisService.getUserState(chatId));
//            System.out.println(redisService.getCache(chatId));
            redisService.setUser(response,chatId);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(SUCCESSFUL_LOGIN_MESSAGE);
            sendMessage.setReplyMarkup(keyboardFactory.replyForLoginKeyBoard());
            return sendMessage;
        }


    }

    public SendMessage replyForType(Long chatId, String message) {
//        System.out.println(redisService.getUserState(chatId));
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
                redisService.setState(chatId,UserState.STUDENT_TYPE);
                return sendMessage;
            } else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(TEACHER_CHAT_TYPE);
                sendMessage.setReplyMarkup(keyboardFactory.replyForTeacherSearchKeyBoard());
                redisService.setState(chatId,UserState.TEACHER_TYPE);
                return sendMessage;
            }
        }
    }

    public SendMessage replyForStudentOrTeacherType(Long chatId, String userName){
        if(userName.equals("ListStudents")){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("please select the username that you want to send messagw");
            sendMessage.setReplyMarkup(keyboardFactory.replyForListTeacherStudent(userService.getAllStudent()));
//            redisService.setUserState(chatId,UserState.LIST_OF_STUDENT);
            return  sendMessage;
        } else if (userName.equals("ListTeachers")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("please select the username that you want to send message");
            sendMessage.setReplyMarkup(keyboardFactory.replyForListTeacherStudent(userService.getAllTeacher()));
//            redisService.setUserState(chatId,UserState.LIST_OF_TEACHER);
            return  sendMessage;
        }
        else{
            if(redisService.getUser(userName)==null)
            {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(ID_NOTFOUND);
                sendMessage.setReplyMarkup(keyboardFactory.replyForLoginKeyBoard());
                redisService.setState(chatId,UserState.LOGGEDIN);
                return sendMessage;
            }
            else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(idFound(userName));

                UserDto userDto = redisService.getCache(chatId);
                redisService.setCache(chatId,UserDto.builder().state(UserState.MESSAGE).username(userDto.getUsername()).receiverChatId(redisService.getUser(userName)).build());
                return sendMessage;
            }
        }
    }

    public List<SendMessage> replyForMessage(Long chatId, String message){
        SendMessage receiverMessage = new SendMessage();
        receiverMessage.setChatId(redisService.getCache(chatId).getReceiverChatId());
        receiverMessage.setText(message(redisService.getCache(chatId).getUsername(),message));
        receiverMessage.setReplyMarkup(keyboardFactory.replyForCancelReply());
        UserDto userDto = redisService.getCache(redisService.getCache(chatId).getReceiverChatId());
        userDto.setReceiverChatId(chatId);
        userDto.setState(UserState.MESSAGE);
        redisService.setCache(userDto.getReceiverChatId(),userDto);
        SendMessage senderMessage = new SendMessage();
        senderMessage.setChatId(chatId);
        senderMessage.setText(SENT);
        senderMessage.setReplyMarkup(keyboardFactory.replyForLoginKeyBoard());
        redisService.setState(chatId, UserState.LOGGEDIN);
        return List.of(receiverMessage,senderMessage);
    }



}
