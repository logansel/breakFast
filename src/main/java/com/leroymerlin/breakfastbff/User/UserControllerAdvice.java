package com.leroymerlin.breakfastbff.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserControllerAdvice {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> userNotFoundException(Exception exception, WebRequest request) {
    return new ResponseEntity<>(UserErrorResponse.create(LocalDateTime.now(),
        exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
  }

  @Data
  public static class UserErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String details;


    public UserErrorResponse(LocalDateTime timestamp, String message, String details) {
      this.timestamp = timestamp;
      this.message = message;
      this.details = details;
    }

    public static UserErrorResponse create(LocalDateTime timestamp, String message,
        String details) {
      return new UserErrorResponse(timestamp, message, details);
    }
  }
}

