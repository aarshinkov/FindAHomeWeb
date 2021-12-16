package com.plamena.findahomeweb;

import com.plamena.findahomeweb.api.AuthApi;
import com.plamena.findahomeweb.api.EstatesApi;
import com.plamena.findahomeweb.api.UsersApi;
import com.plamena.findahomeweb.security.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class FindAHomeWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(FindAHomeWebApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    return restTemplate;
  }

  @Bean
  public AuthApi authApi() {
    return new AuthApi();
  }

  @Bean
  public UsersApi usersApi() {
    return new UsersApi();
  }

  @Bean
  public EstatesApi estatesApi() {
    return new EstatesApi();
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  public AuthenticationFailureHandler authFailureHandler() {
    CustomAuthFailureHandler customAuthFailureHandler = new CustomAuthFailureHandler();
    customAuthFailureHandler.setDefaultFailureUrl("/login");
    return customAuthFailureHandler;
  }

  @Bean
  public AuthenticationSuccessHandler authSuccessHandler() {
    CustomAuthSuccessHandler customAuthSuccessHandler = new CustomAuthSuccessHandler();
    customAuthSuccessHandler.setUseReferer(true);
    return customAuthSuccessHandler;
  }

  @Bean
  public AuthenticationProvider authProvider() {
    return new CustomAuthenticationProvider();
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new CustomLogoutSuccessHandler();
  }

}
