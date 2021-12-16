package com.plamena.findahomeweb.security;

import com.plamena.findahomeweb.api.UsersApi;
import com.plamena.findahomeweb.responses.users.UserGetResponse;
import com.plamena.findahomeweb.services.SystemService;
import com.plamena.findahomeweb.utils.AppConstants;
import com.plamena.findahomeweb.utils.Names;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final RequestCache requestCache = new HttpSessionRequestCache();

  @Autowired
  private UsersApi usersApi;

  @Autowired
  private SystemService systemService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

    CustomAuthenticationToken auth = (CustomAuthenticationToken) authentication;

    HttpSession session = request.getSession();

    session.setAttribute(AppConstants.SESSION_AUTHORIZATION, "Bearer " + auth.getAuthToken());

    UserGetResponse user = usersApi.getUser(auth.getName(), request);
    session.setAttribute(AppConstants.SESSION_USER_ID, user.getUserId());

    session.setAttribute("createdOn", user.getCreatedOn());
    session.setAttribute("email", user.getEmail());
    session.setAttribute("roles", user.getRoles());

    Names name = Names.builder().firstName(user.getFirstName()).lastName(user.getLastName()).build();
    session.setAttribute(AppConstants.SESSION_USER, name);

    SavedRequest savedRequest = requestCache.getRequest(request, response);

    if (savedRequest == null) {
      super.onAuthenticationSuccess(request, response, authentication);

      return;
    }

    String targetUrlParameter = getTargetUrlParameter();
    if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
      requestCache.removeRequest(request, response);
      super.onAuthenticationSuccess(request, response, authentication);

      return;
    }

    clearAuthenticationAttributes(request);

    // Use the DefaultSavedRequest URL
    String targetUrl = savedRequest.getRedirectUrl();
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
