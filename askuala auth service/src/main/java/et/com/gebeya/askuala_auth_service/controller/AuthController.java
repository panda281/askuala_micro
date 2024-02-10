package et.com.gebeya.askuala_auth_service.controller;


import et.com.gebeya.askuala_auth_service.dto.request_dto.UsersCredential;
import et.com.gebeya.askuala_auth_service.dto.request_dto.ValidationRequest;
import et.com.gebeya.askuala_auth_service.dto.response_dto.AuthenticationResponse;
import et.com.gebeya.askuala_auth_service.dto.response_dto.ValidationResponse;
import et.com.gebeya.askuala_auth_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    @PostMapping("/validate")
    public ResponseEntity<ValidationResponse> validate(@RequestBody ValidationRequest request)
    {
        return authenticationService.validate(request);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UsersCredential credential)
    {
        return authenticationService.signIn(credential);
    }
}
