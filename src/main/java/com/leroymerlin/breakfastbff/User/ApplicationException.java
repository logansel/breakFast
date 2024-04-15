package com.leroymerlin.breakfastbff.User;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Generic class to manage exceptions in application.
 */
@Getter
public class ApplicationException extends RuntimeException {

  private final HttpStatus errCode;
  private final String errMsg;

  /**
   * Constructor of the exception to return the status and the error message at the application.
   *
   * @param errCode Error code, like 4xx or 5xx.
   * @param errMsg  Error message to send.
   */
  public ApplicationException(HttpStatus errCode, String errMsg) {
    super(errMsg);
    this.errCode = errCode;
    this.errMsg = errMsg;
  }
}

