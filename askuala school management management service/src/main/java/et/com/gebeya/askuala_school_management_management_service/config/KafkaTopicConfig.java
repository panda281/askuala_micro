package et.com.gebeya.askuala_school_management_management_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static et.com.gebeya.askuala_school_management_management_service.util.Constant.*;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic paymentServiceTopic(){
        return TopicBuilder.name(PAYMENT_TOPIC).build();
    }

    @Bean
    public NewTopic addAccountTopic(){
        return TopicBuilder.name(ADD_ACCOUNT_TOPIC).build();
    }

    @Bean
    public NewTopic updateAccountTopic(){
        return TopicBuilder.name(UPDATE_ACCOUNT_TOPIC).build();
    }

    @Bean
    public NewTopic paymentSuccessfulTopic(){
        return TopicBuilder.name(PAYMENT_SUCCESSFUL_TOPIC).build();
    }

    @Bean
    public NewTopic paymentPendingTopic(){
        return TopicBuilder.name(PAYMENT_PENDING_TOPIC).build();
    }
}
