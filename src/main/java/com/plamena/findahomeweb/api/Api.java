package com.plamena.findahomeweb.api;

import com.plamena.findahomeweb.base.Base;
import com.plamena.findahomeweb.services.SystemService;
import com.plamena.findahomeweb.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public abstract class Api extends Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private SystemService systemService;

  protected HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/json");
    headers.set("Content-Type", "application/json");

    return headers;
  }

  protected String getAuthorizationToken(HttpServletRequest request) {
    return (String) systemService.getSessionAttribute(request, AppConstants.SESSION_AUTHORIZATION);
  }
}
