package com.plamena.findahomeweb.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private String authToken;

  public CustomAuthenticationToken(Object principal, Object credentials, String token) {
    super(principal, credentials);
    this.authToken = token;
  }

  public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
    super(principal, credentials, authorities);
    this.authToken = token;
  }
}
