package com.plamena.findahomeweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationProvider authProvider;

  @Autowired
  private AuthenticationSuccessHandler authSuccessHandler;

  @Autowired
  private LogoutSuccessHandler logoutSuccessHandler;

  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/signup").anonymous()
            .antMatchers("/logout").authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/authentication")
            .loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .successHandler(authSuccessHandler)
            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and()
            .logout()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler)
            .and()
            .httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider);
  }
}
