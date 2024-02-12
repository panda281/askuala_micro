package et.com.gebeya.askuala_comm.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component

public class MessageBuilder {
    public SendMessage welcomeMessage(Long chatId)
    {
        return SendMessage.builder().chatId(chatId)
                .text(Constants.WELCOME_MESSAGE)
                .build();
    }
}
