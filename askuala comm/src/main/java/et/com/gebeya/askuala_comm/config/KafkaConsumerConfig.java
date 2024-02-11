package et.com.gebeya.askuala_comm.config;

import et.com.gebeya.askuala_comm.dto.AddAccountRequestDto;
import et.com.gebeya.askuala_comm.dto.PaymentPendingRequestDto;
import et.com.gebeya.askuala_comm.dto.PaymentSuccessfulRequestDto;
import et.com.gebeya.askuala_comm.dto.UpdateAccountRequestDto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    private String bootstrapServer = "http://localhost:9092";

    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    factory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }

    @Bean
    public ConsumerFactory<String, AddAccountRequestDto> addAccountConsumerFactory() {
        JsonDeserializer<AddAccountRequestDto> jsonDeserializer = new JsonDeserializer<>(AddAccountRequestDto.class, false);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), new ErrorHandlingDeserializer<>(jsonDeserializer));
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, AddAccountRequestDto>> addAccountListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AddAccountRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(addAccountConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, UpdateAccountRequestDto> updateAccountConsumerFactory() {
        JsonDeserializer<UpdateAccountRequestDto> jsonDeserializer = new JsonDeserializer<>(UpdateAccountRequestDto.class, false);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), new ErrorHandlingDeserializer<>(jsonDeserializer));
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UpdateAccountRequestDto>> updateAccountListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UpdateAccountRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(updateAccountConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PaymentSuccessfulRequestDto> paymentSuccessfulConsumerFactory() {
        JsonDeserializer<PaymentSuccessfulRequestDto> jsonDeserializer = new JsonDeserializer<>(PaymentSuccessfulRequestDto.class, false);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), new ErrorHandlingDeserializer<>(jsonDeserializer));
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, PaymentSuccessfulRequestDto>> paymentSuccessfulListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentSuccessfulRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentSuccessfulConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PaymentPendingRequestDto> paymentPendingConsumerFactory() {
        JsonDeserializer<PaymentPendingRequestDto> jsonDeserializer = new JsonDeserializer<>(PaymentPendingRequestDto.class, false);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), new ErrorHandlingDeserializer<>(jsonDeserializer));
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, PaymentPendingRequestDto>> paymentPendingListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentPendingRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentPendingConsumerFactory());
        return factory;
    }

}
