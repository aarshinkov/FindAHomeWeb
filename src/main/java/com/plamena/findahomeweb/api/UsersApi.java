package com.plamena.findahomeweb.api;

import com.google.gson.Gson;
import com.plamena.findahomeweb.requests.estates.EstateCreateRequest;
import com.plamena.findahomeweb.requests.users.UserCreateRequest;
import com.plamena.findahomeweb.responses.estates.EstateGetResponse;
import com.plamena.findahomeweb.responses.users.UserGetResponse;
import com.plamena.findahomeweb.utils.LogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static com.plamena.findahomeweb.utils.Methods.GET;
import static com.plamena.findahomeweb.utils.Methods.POST;

public class UsersApi extends Api {

  private final Gson PARSER = new Gson();

  @Autowired
  private RestTemplate restTemplate;

  public UserGetResponse getUser(String identifier, HttpServletRequest request) {
    HttpHeaders headers = getHeaders();
    headers.set("Authorization", getAuthorizationToken(request));

    HttpEntity entity = new HttpEntity(headers);

    final String url = getApiUrl() + "/api/v1/users/" + identifier;

    LogRequest logRequest = new LogRequest(GET.getMethod(), url);

    ResponseEntity<UserGetResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, UserGetResponse.class);

    return response.getBody();
  }

  public UserGetResponse createUser(UserCreateRequest ucr, HttpServletRequest request) {
    HttpHeaders headers = getHeaders();

    HttpEntity entity = new HttpEntity(ucr, headers);

    final String url = getApiUrl() + "/api/v1/users";

    LogRequest logRequest = new LogRequest(POST.getMethod(), url);

    ResponseEntity<UserGetResponse> response = restTemplate.postForEntity(url, entity, UserGetResponse.class);

    return response.getBody();
  }
}
