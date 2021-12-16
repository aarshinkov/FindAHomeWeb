package com.plamena.findahomeweb.responses.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Login implements Serializable {

  @Email(message = "Имейлът е невалиден!")
  @NotEmpty(message = "Полето не може да бъде празно!")
  private String email;

  @NotEmpty(message = "Полето не може да бъде празно!")
  private String password;
}
