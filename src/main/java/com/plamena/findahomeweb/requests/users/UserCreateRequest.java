package com.plamena.findahomeweb.requests.users;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateRequest implements Serializable {

  private String email;
  private String password;
  private String firstName;
  private String lastName;
}
