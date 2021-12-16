package com.plamena.findahomeweb.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthException extends RuntimeException {

  private Integer code;
  private String message;
  private String details;
}
