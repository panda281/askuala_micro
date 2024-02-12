package et.com.gebeya.askuala_comm.telegram;

import et.com.gebeya.askuala_comm.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class TelegramService {
    private final WebClient.Builder webClientBuilder;
    public Boolean login(LoginRequestDto request){
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8181/api/v1/auth/login")
                .header("Content-Type", "application/json")
                .body(Mono.just(request), LoginRequestDto.class)
                .retrieve()
                .bodyToMono(Void.class).then(Mono.fromCallable(() -> true))
                .onErrorResume(error -> Mono.just(false)).block();
    }

}
