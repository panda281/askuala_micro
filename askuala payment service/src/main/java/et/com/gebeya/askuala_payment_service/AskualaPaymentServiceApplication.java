package et.com.gebeya.askuala_payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AskualaPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AskualaPaymentServiceApplication.class, args);
	}

}
