package et.com.gebeya.askuala_comm.telegram;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotStarter extends TelegramLongPollingBot {
    private final String username;
//    private final KeyboardFactory keyboardFactory;
    private final MessageBuilder messageBuilder;

    public BotStarter(@Value("${app.telegram.username}") String username,
                           @Value("${app.telegram.token}") String botToken,MessageBuilder messageBuilder
    ) {
        super(botToken);
        this.username = username;
        this.messageBuilder=messageBuilder;
    }


    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if(update.hasMessage())
        {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String text = message.getText();

            if(text.equals("/start"))
            {
                execute( messageBuilder.welcomeMessage(chatId));
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }
}
