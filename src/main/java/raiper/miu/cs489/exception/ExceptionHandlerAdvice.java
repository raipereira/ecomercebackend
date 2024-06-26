package raiper.miu.cs489.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import raiper.miu.cs489.result.Result;
import raiper.miu.cs489.result.StatusCode;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result AddressNotFoundExceptionHandler(Exception ex) {
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result authenticationExceptionHandler(Exception ex) {
        return new Result(false, StatusCode.UNAUTHORIZED, "User name or password is incorrect", ex.getMessage());
    }
}
