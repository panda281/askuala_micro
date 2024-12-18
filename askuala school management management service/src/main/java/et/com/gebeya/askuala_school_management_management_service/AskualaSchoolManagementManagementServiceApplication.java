package et.com.gebeya.askuala_school_management_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class AskualaSchoolManagementManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AskualaSchoolManagementManagementServiceApplication.class, args);
	}

}
