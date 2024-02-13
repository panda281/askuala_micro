package et.com.gebeya.askuala_comm.telegram;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class BotStarter extends TelegramLongPollingBot {
    private final String username;
    //    private final KeyboardFactory keyboardFactory;
    private final MessageBuilder messageBuilder;
    private final RedisService redisService;
    private final ResponseHandler responseHandler;

    public BotStarter(@Value("${app.telegram.username}") String username,
                      @Value("${app.telegram.token}") String botToken, MessageBuilder messageBuilder,
                      RedisService redisService,
                      ResponseHandler responseHandler
    ) {
        super(botToken);
        this.username = username;
        this.messageBuilder = messageBuilder;
        this.redisService = redisService;
        this.responseHandler = responseHandler;

    }


    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            Long chatId = message.getChatId();
            String text = message.getText();

            if (text.equals("/start") && redisService.getUserState(chatId)==null) {
                execute(responseHandler.replyForWelcome(chatId));
            } else if (text.equals("/start") && redisService.getUserState(chatId) != null && redisService.getUserState(chatId) != UserState.START) {
                execute(responseHandler.replyForAlreadyLoginUser(chatId));
            } else if (redisService.getUserState(chatId) != null && redisService.getUserState(chatId).equals(UserState.START)) {
                execute(responseHandler.replyForLogin(chatId, text));
            } else if (redisService.getUserState(chatId) != null && redisService.getUserState(chatId).equals(UserState.LOGGED_IN)) {
                execute(responseHandler.replyForType(chatId, text));
            } else if (redisService.getUserState(chatId) != null && redisService.getUserState(chatId).equals(UserState.STUDENT_TYPE)) {
                execute(responseHandler.replyForStudentOrTeacherType(chatId, text));
            } else if (redisService.getUserState(chatId) != null && redisService.getUserState(chatId).equals(UserState.TEACHER_TYPE)) {
                execute(responseHandler.replyForStudentOrTeacherType(chatId, text));
            } else if (redisService.getUserState(chatId) != null && redisService.getUserState(chatId).equals(UserState.MESSAGE)) {
                List<SendMessage> sendMessages = responseHandler.replyForMessage(chatId, text);
                execute(sendMessages.get(0));
                execute(sendMessages.get(1));
            }

        }
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = SendMessage.builder().chatId(chatId).text(text).build();
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }
}
