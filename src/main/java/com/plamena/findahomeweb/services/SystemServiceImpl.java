package com.plamena.findahomeweb.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SystemServiceImpl implements SystemService {

  @Override
  public Object getSessionAttribute(HttpServletRequest request, String attributeName) {
    HttpSession session = request.getSession();
    return session.getAttribute(attributeName);
  }
}
