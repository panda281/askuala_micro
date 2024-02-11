package et.com.gebeya.askuala_comm.listeners;

import et.com.gebeya.askuala_comm.dto.AddAccountRequestDto;
import et.com.gebeya.askuala_comm.dto.PaymentPendingRequestDto;
import et.com.gebeya.askuala_comm.dto.PaymentSuccessfulRequestDto;
import et.com.gebeya.askuala_comm.dto.UpdateAccountRequestDto;
import et.com.gebeya.askuala_comm.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static et.com.gebeya.askuala_comm.util.Constant.*;

@Component
@RequiredArgsConstructor
public class KafkaListeners {
    private final MessageService messageService;

    @KafkaListener(topics = ADD_ACCOUNT_TOPIC, groupId = "group2",containerFactory = "addAccountListenerFactory")
    void addAccountListener(AddAccountRequestDto dto) {
        System.out.println(dto);
        messageService.addAccount(dto);
    }

    @KafkaListener(topics = UPDATE_ACCOUNT_TOPIC,groupId = "group2", containerFactory = "updateAccountListenerFactory")
    void updateAccountListener(UpdateAccountRequestDto dto) {
        System.out.println(dto);
        messageService.updateAccount(dto);
    }

    @KafkaListener(topics = PAYMENT_PENDING_TOPIC,groupId = "group2", containerFactory = "paymentPendingListenerFactory")
    void paymentPendingListener(PaymentPendingRequestDto dto) {
        System.out.println(dto);
        messageService.paymentPending(dto);
    }

    @KafkaListener(topics = PAYMENT_SUCCESSFUL_TOPIC,groupId = "group2", containerFactory = "paymentSuccessfulListenerFactory")
    void paymentSuccessfulListener(PaymentSuccessfulRequestDto dto) {
        System.out.println(dto);
        messageService.paymentSuccessful(dto);
    }
}
