package com.plamena.findahomeweb.utils;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Names implements Serializable {

  private String firstName;
  private String lastName;

  public String getFullName() {
    return lastName == null ? firstName : firstName + " " + lastName;
  }

  @Override
  public String toString() {
    return getFullName();
  }
}
