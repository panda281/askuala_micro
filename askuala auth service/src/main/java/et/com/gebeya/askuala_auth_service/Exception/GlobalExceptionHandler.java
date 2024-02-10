package et.com.gebeya.askuala_auth_service.Exception;

import et.com.gebeya.askuala_auth_service.dto.response_dto.ErrorMessageDto;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorMessageDto> handleException(ExpiredJwtException ex)
    {
        ErrorMessageDto errorObject = ErrorMessageDto.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
    }
}
