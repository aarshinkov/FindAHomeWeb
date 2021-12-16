package com.plamena.findahomeweb.responses.auth;

import com.plamena.findahomeweb.responses.users.UserGetResponse;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthenticationResponse implements Serializable {

  private String tokenType;
  private String accessToken;
  private List<String> access;
  private UserGetResponse user;
}
