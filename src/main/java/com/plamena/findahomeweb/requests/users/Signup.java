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
public class Signup implements Serializable {

  @Email(message = "Имейлът е невалиден!")
  @NotEmpty(message = "Полето не може да бъде празно!")
  private String email;

  @NotEmpty(message = "Полето не може да бъде празно!")
  private String password;

  @NotEmpty(message = "Полето не може да бъде празно!")
  private String confirmPassword;

  @NotEmpty(message = "Полето не може да бъде празно!")
  private String firstName;

  private String lastName;
}
