package springboot.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Passwords don't match.")
public class PasswordsDontMatchException extends RuntimeException {

}
