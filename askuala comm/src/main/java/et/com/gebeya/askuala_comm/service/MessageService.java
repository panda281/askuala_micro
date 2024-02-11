package et.com.gebeya.askuala_comm.service;

import et.com.gebeya.askuala_comm.dto.AddAccountRequestDto;
import et.com.gebeya.askuala_comm.dto.PaymentPendingRequestDto;
import et.com.gebeya.askuala_comm.dto.PaymentSuccessfulRequestDto;
import et.com.gebeya.askuala_comm.dto.UpdateAccountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static et.com.gebeya.askuala_comm.util.MessageTemplate.*;

@Component
@RequiredArgsConstructor
public class MessageService {
    private final EmailService emailService;

    public void addAccount(AddAccountRequestDto request){
        String message = addAccountMessage(request.getName(),request.getUsername(),request.getPassword());
        String subject = "NOTIFYING ACCOUNT CREATION";
        emailService.sendEmail(request.getEmail(),subject,message);
    }

    public void updateAccount(UpdateAccountRequestDto request){
        String message = updateAccountMessage(request.getName());
        String subject = "NOTIFYING ACCOUNT UPDATE";
        emailService.sendEmail(request.getEmail(),subject,message);
    }

    public void paymentPending(PaymentPendingRequestDto request){
        String message = paymentPendingMessage(request.getName(), request.getPrice(), request.getMonth());
        String subject = request.getMonth() + " Tuition Fee Reminder";
        emailService.sendEmail(request.getEmail(),subject,message);
    }

    public void paymentSuccessful(PaymentSuccessfulRequestDto request){
        String message = paymentSuccessfulMessage(request.getName(), request.getMonth());
        String subject = "SUCCESSFUL PAYMENT RECIEVED";
        emailService.sendEmail(request.getEmail(),subject,message);
    }
}
