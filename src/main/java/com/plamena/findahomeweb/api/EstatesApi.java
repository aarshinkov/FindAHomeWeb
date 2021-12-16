package com.plamena.findahomeweb.api;

import com.google.gson.Gson;
import com.plamena.findahomeweb.requests.estates.EstateCreateRequest;
import com.plamena.findahomeweb.requests.estates.EstateEditRequest;
import com.plamena.findahomeweb.responses.estates.EstateGetResponse;
import com.plamena.findahomeweb.responses.estates.EstatesCollection;
import com.plamena.findahomeweb.responses.users.UserGetResponse;
import com.plamena.findahomeweb.utils.LogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static com.plamena.findahomeweb.utils.Methods.*;

public class EstatesApi extends Api {

  private final Gson PARSER = new Gson();

  @Autowired
  private RestTemplate restTemplate;

  public EstatesCollection getEstates(Integer page, Integer limit, HttpServletRequest request) {
    HttpHeaders headers = getHeaders();
    headers.set("Authorization", getAuthorizationToken(request));

    HttpEntity entity = new HttpEntity(headers);

    final String url = getApiUrl() + "/api/v1/estates?page=" + page + "&limit=" + limit;

    LogRequest logRequest = new LogRequest(GET.getMethod(), url);

    ResponseEntity<EstatesCollection> response = restTemplate.exchange(url, HttpMethod.GET, entity, EstatesCollection.class);

    return response.getBody();
  }

  public EstateGetResponse getEstate(String estateId, HttpServletRequest request) {
    HttpHeaders headers = getHeaders();
    headers.set("Authorization", getAuthorizationToken(request));

    HttpEntity entity = new HttpEntity(headers);

    final String url = getApiUrl() + "/api/v1/estates/" + estateId;

    LogRequest logRequest = new LogRequest(GET.getMethod(), url);

    ResponseEntity<EstateGetResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, EstateGetResponse.class);

    return response.getBody();
  }

  public EstateGetResponse createEstate(EstateCreateRequest ecr, HttpServletRequest request) {
    HttpHeaders headers = getHeaders();
    headers.set("Authorization", getAuthorizationToken(request));

    HttpEntity entity = new HttpEntity(ecr, headers);

    final String url = getApiUrl() + "/api/v1/estates?userId=" + getLoggedUserId(request);

    LogRequest logRequest = new LogRequest(POST.getMethod(), url);

    ResponseEntity<EstateGetResponse> response = restTemplate.postForEntity(url, entity, EstateGetResponse.class);

    return response.getBody();
  }

  public EstateGetResponse editEstate(EstateEditRequest eer, HttpServletRequest request) {
    HttpHeaders headers = getHeaders();
    headers.set("Authorization", getAuthorizationToken(request));

    HttpEntity entity = new HttpEntity(eer, headers);

    final String url = getApiUrl() + "/api/v1/estates?userId=" + getLoggedUserId(request);

    LogRequest logRequest = new LogRequest(PUT.getMethod(), url);

    ResponseEntity<EstateGetResponse> response = restTemplate.exchange(url, HttpMethod.PUT, entity, EstateGetResponse.class);

    return response.getBody();
  }

  public Boolean deleteEstate(String estateId, HttpServletRequest request) {
    HttpHeaders headers = getHeaders();
    headers.set("Authorization", getAuthorizationToken(request));

    HttpEntity entity = new HttpEntity(headers);

    final String url = getApiUrl() + "/api/v1/estates/" + estateId;

    LogRequest logRequest = new LogRequest(DELETE.getMethod(), url);

    ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, Boolean.class);
    return response.getBody();
  }
}
