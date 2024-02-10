package et.com.gebeya.askualas_gateway.filter;


import et.com.gebeya.askualas_gateway.dto.TokenDto;
import et.com.gebeya.askualas_gateway.dto.ValidationResponseDto;
import et.com.gebeya.askualas_gateway.exception.HeaderNotFoundException;
import et.com.gebeya.askualas_gateway.exception.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


    private final RouteValidator validator;
    private final WebClient.Builder webClientBuilder;


    public AuthenticationFilter(RouteValidator validator, WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.validator = validator;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                // Header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new HeaderNotFoundException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                TokenDto token = TokenDto.builder().token(authHeader).build();
                return validateAuthorization(token)
                        .flatMap(response -> {
                            ServerHttpRequest mutatedHttpRequest = exchange.getRequest().mutate()
                                    .header("Role", response.getRole().toString())
                                    .build();
                            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedHttpRequest).build();
                            return chain.filter(mutatedExchange);
                        })
                        .onErrorResume(error -> {
                            // Handle validation error
                            return chain.filter(exchange);
                        });
            }
            return chain.filter(exchange);
        });
    }


//    private Mono<ValidationResponseDto> identityValidator(TokenDto token) {
//
//
////        WebClient.RequestHeadersSpec<?> headersSpec = webClientBuilder.build().post()
////                .uri("http://Auth-Service/api/v1/auth/validate")
////                .contentType(MediaType.APPLICATION_JSON)
////                .bodyValue(token);
////
////
////        return headersSpec.exchangeToMono(response -> {
////            if (response.statusCode().equals(HttpStatus.OK)) {
////                return response.bodyToMono(ValidationResponseDto.class);
////            } else if (response.statusCode().equals(HttpStatus.UNAUTHORIZED)) {
////                return Mono.error(new UnAuthorizedException("Unauthorized access"));
////            } else {
////                return response.createException()
////                        .flatMap(Mono::error);
////            }
////        });
//
//        Mono<ResponseEntity<ValidationResponseDto>> responseMono = webClientBuilder.build()
//                .post()
//                .uri("http://Auth-Service/api/v1/auth/validate")
//                .header("Content-Type", "application/json")
//                .body(Mono.just(token), TokenDto.class)
//                .retrieve()
//                .toEntity(ValidationResponseDto.class);
//
//        return responseMono.blockOptional()
//                .map(ResponseEntity::getBody)
//                .orElseThrow(() -> new RuntimeException("Error occurred"));
//
//
//    }

    public Mono<ValidationResponseDto> validateAuthorization(TokenDto token) {
        return webClientBuilder.build()
                .post()
                .uri("http://Auth-Service/api/v1/auth/validate")
                .header("Content-Type", "application/json")
                .body(Mono.just(token), TokenDto.class)
                .retrieve()
                .toEntity(ValidationResponseDto.class)
                .map(ResponseEntity::getBody)
                .onErrorResume(error -> {
                    // Handle errors gracefully, e.g., log and return a user-friendly message
//                    log.error("Error occurred during validation:", error);
                    return Mono.error(new UnAuthorizedException("UnAuthorized"));
                });
    }

    public static class Config {

    }
}
