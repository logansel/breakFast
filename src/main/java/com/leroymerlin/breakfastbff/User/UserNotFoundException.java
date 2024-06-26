package com.leroymerlin.breakfastbff.User;

import com.leroymerlin.breakfastbff.HandlerException.ApplicationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends ApplicationException {
  public UserNotFoundException(HttpStatus errCode, String message) {
    super(errCode, message);
  }
}
