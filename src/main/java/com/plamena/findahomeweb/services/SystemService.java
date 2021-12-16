package com.plamena.findahomeweb.services;

import javax.servlet.http.HttpServletRequest;

public interface SystemService {

  Object getSessionAttribute(HttpServletRequest request, String attributeName);
}
