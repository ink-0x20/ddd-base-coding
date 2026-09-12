package com.inkblogdb.ddd.presentation.error;

import com.inkblogdb.ddd.application.usecase.abort.AbortException;
import com.inkblogdb.ddd.application.usecase.abort.NotFountException;
import com.inkblogdb.ddd.domain.CustomException;
import com.inkblogdb.ddd.domain.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NotFountException.class})
  public void notFoundError() {
  }

  @ExceptionHandler({AbortException.class})
  public ResponseEntity<Object> abortError(CustomException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(
            exception.type(),
            exception.message(),
            exception.detail()
        ),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({DomainException.class})
  public ResponseEntity<Object> domainError(CustomException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(
            exception.type(),
            exception.message(),
            exception.detail()
        ),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({Throwable.class})
  public ResponseEntity<Object> unknownError(Throwable error) {
    log.error("予期せぬエラーが発生", error);
    return new ResponseEntity<>(
        new ErrorResponse(
            "SystemError",
            "予期せぬエラーが発生",
            error.getMessage()
        ),
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
