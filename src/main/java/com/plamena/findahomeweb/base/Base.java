package com.plamena.findahomeweb.base;

import com.plamena.findahomeweb.services.SystemService;
import com.plamena.findahomeweb.utils.AppConstants;
import com.plamena.findahomeweb.utils.Names;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class Base {

  @Autowired
  private SystemService systemService;

  protected String getLoggedUserId(HttpServletRequest request) {
    return (String) systemService.getSessionAttribute(request, AppConstants.SESSION_USER_ID);
  }

  protected Names getLoggedUserName(HttpServletRequest request) {
    return (Names) systemService.getSessionAttribute(request, AppConstants.SESSION_USER);
  }

  protected String getApiUrl() {
    return "http://localhost:8090/FindAHomeAPI";
  }
}
