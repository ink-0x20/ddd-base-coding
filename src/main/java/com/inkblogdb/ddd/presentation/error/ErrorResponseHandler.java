package com.inkblogdb.ddd.presentation.error;

import com.inkblogdb.ddd.application.usecase.abort.AbortException;
import com.inkblogdb.ddd.application.usecase.abort.NotFoundException;
import com.inkblogdb.ddd.domain.CustomException;
import com.inkblogdb.ddd.domain.DomainException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NotFoundException.class})
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
        HttpHeaders.EMPTY,
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
        HttpHeaders.EMPTY,
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> pathParameterError(ConstraintViolationException exception) {
    return new ResponseEntity<>(
        new ErrorResponse(
            "ApiInterfaceError.ValidationInvalid.PathParameter",
            exception.getMessage(),
            null
        ),
        HttpHeaders.EMPTY,
        HttpStatus.BAD_REQUEST);
  }
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    Optional<FieldError> fieldError = Optional.ofNullable(
        exception.getBindingResult().getFieldError()
    );
    if (fieldError.isPresent()) {
      return new ResponseEntity<>(
          new ErrorResponse(
              "ApiInterfaceError.ValidationInvalid.Request",
              Objects.requireNonNullElse(fieldError.get().getDefaultMessage(), "入力値が不正"),
              fieldError.get().getField()
          ),
          HttpHeaders.EMPTY,
          HttpStatus.BAD_REQUEST);
    }

    Optional<ObjectError> globalError = Optional.ofNullable(
        exception.getBindingResult().getGlobalError()
    );
    if (globalError.isPresent()) {
      return new ResponseEntity<>(
          new ErrorResponse(
              "ApiInterfaceError.ValidationInvalid.Request",
              Objects.requireNonNullElse(globalError.get().getDefaultMessage(), "入力値が不正"),
              null
          ),
          HttpHeaders.EMPTY,
          HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(
        new ErrorResponse(
            "ApiInterfaceError.ValidationInvalid.Request",
            exception.getMessage(),
            null
        ),
        HttpHeaders.EMPTY,
        HttpStatus.INTERNAL_SERVER_ERROR);
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
        HttpHeaders.EMPTY,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
