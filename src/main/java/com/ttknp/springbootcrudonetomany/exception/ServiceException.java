package com.ttknp.springbootcrudonetomany.exception;

import com.ttknp.springbootcrudonetomany.exception.entity.DetailsException;
import com.ttknp.springbootcrudonetomany.exception.handler.NotAllowedMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDate;
@ControllerAdvice
public class ServiceException {
    @ExceptionHandler(NotAllowedMethod.class)
    public ResponseEntity<DetailsException> setHandlerNotAllowedMethod(NotAllowedMethod notAllowedMethod) {
        DetailsException detailsException = new DetailsException((short)HttpStatus.METHOD_NOT_ALLOWED.value() , notAllowedMethod.getMessage(), LocalDate.now());
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(detailsException);
    }
    @ExceptionHandler
    public ResponseEntity<DetailsException> setHandlerAnyException(Exception exception) {
        DetailsException detailsException = new DetailsException((short)HttpStatus.BAD_REQUEST.value() , exception.getMessage(), LocalDate.now());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(detailsException);
    }

}
