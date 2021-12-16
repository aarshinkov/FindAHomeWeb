package com.plamena.findahomeweb.security;

import com.plamena.findahomeweb.api.AuthApi;
import com.plamena.findahomeweb.exceptions.AuthException;
import com.plamena.findahomeweb.responses.auth.Authenticated;
import com.plamena.findahomeweb.responses.auth.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private AuthApi authApi;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    Login login = new Login(email, password);

    Authenticated authenticated;
    try {
      authenticated = authApi.login(login);
      return new CustomAuthenticationToken(authenticated.getEmail(), password, authenticated.getRoles(), authenticated.getAuthToken());
    } catch (AuthException ex) {
    }

//    if (authenticated != null)
//    {
//    }
    return null;

  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
