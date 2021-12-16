package com.plamena.findahomeweb.responses.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Authenticated implements Serializable {

  private String email;
  private String authToken;
  private List<GrantedAuthority> roles = new ArrayList<>();
}
