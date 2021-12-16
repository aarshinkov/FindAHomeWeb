package com.plamena.findahomeweb.api;

import com.google.gson.Gson;
import com.plamena.findahomeweb.exceptions.AuthException;
import com.plamena.findahomeweb.responses.auth.Authenticated;
import com.plamena.findahomeweb.responses.auth.AuthenticationResponse;
import com.plamena.findahomeweb.responses.auth.Login;
import com.plamena.findahomeweb.utils.LogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.plamena.findahomeweb.utils.Methods.POST;

public class AuthApi extends Api {

  private final Gson PARSER = new Gson();

  @Autowired
  private RestTemplate restTemplate;

  public Authenticated login(Login login) throws AuthException {
    HttpHeaders headers = getHeaders();

    HttpEntity entity = new HttpEntity(login, headers);

    final String url = getApiUrl() + "/api/v1/login";

    LogRequest logRequest = new LogRequest(POST.getMethod(), url);

    try {
      ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(url, entity, AuthenticationResponse.class);

      AuthenticationResponse result = response.getBody();

      List<GrantedAuthority> authorities = new ArrayList<>();

//      for (GrantedAuthority access : result.getAccess())
//      {
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + access.getAuthority()));
//      }

      result.getAccess().forEach((authority)
              -> {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + authority));
      });

      Authenticated authenticated = new Authenticated();
      authenticated.setEmail(login.getEmail());
      authenticated.setAuthToken(result.getAccessToken());
      authenticated.setRoles(authorities);

      return authenticated;
    } catch (HttpClientErrorException e) {
      String responseBody = e.getResponseBodyAsString();

      throw PARSER.fromJson(responseBody, AuthException.class);
    }
  }
}
