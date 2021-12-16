package com.plamena.findahomeweb.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogRequest implements Serializable {

  private String method;
  private String url;

  @Override
  public String toString() {
    return method.toUpperCase() + " " + url;
  }
}
