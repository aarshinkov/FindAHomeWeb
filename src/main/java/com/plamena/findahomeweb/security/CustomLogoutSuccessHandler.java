package com.plamena.findahomeweb.security;

import com.plamena.findahomeweb.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

  @Autowired
  private SystemService systemService;

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    HttpSession session = request.getSession();

//    request.getSession().setAttribute("msgInfo", messageSource.getMessage("success.msg.logout", null, new Locale(locale)));
    response.sendRedirect(request.getContextPath() + "/login");

    super.onLogoutSuccess(request, response, authentication);
  }

}
