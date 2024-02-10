package et.com.gebeya.askualas_gateway.exception;

import et.com.gebeya.askualas_gateway.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HeaderNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleHeaderNotFoundException(HeaderNotFoundException exception)
    {
        ErrorMessageDto errorObject = ErrorMessageDto.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorMessageDto> handleUnAuthorizedException(HeaderNotFoundException exception)
    {
        ErrorMessageDto errorObject = ErrorMessageDto.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
    }
}
