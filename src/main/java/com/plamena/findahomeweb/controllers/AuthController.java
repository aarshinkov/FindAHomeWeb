package com.plamena.findahomeweb.controllers;

import com.plamena.findahomeweb.api.AuthApi;
import com.plamena.findahomeweb.api.UsersApi;
import com.plamena.findahomeweb.requests.users.Signup;
import com.plamena.findahomeweb.requests.users.UserCreateRequest;
import com.plamena.findahomeweb.responses.auth.Login;
import com.plamena.findahomeweb.responses.users.UserGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthController {

  @Autowired
  private AuthApi authApi;

  @Autowired
  private UsersApi usersApi;

  @GetMapping("/login")
  public String prepareLogin(Model model) {

    model.addAttribute("login", new Login());

    model.addAttribute("menu", "login");

    return "auth/login";
  }

  @GetMapping("/signup")
  public String prepareSignup(Model model) {

    model.addAttribute("signup", new Signup());

    model.addAttribute("menu", "signup");

    return "auth/signup";
  }

  @PostMapping("/signup")
  public String signup(@ModelAttribute("signup") @Valid Signup signup, BindingResult bindingResult, HttpServletRequest request, Model model) {

    if (!signup.getPassword().equals(signup.getConfirmPassword())) {
      bindingResult.rejectValue("password", null, "Паролите не съвпадат");
      bindingResult.rejectValue("confirmPassword", null, "Паролите не съвпадат");
    }

    if (bindingResult.hasErrors()) {
      model.addAttribute("menu", "signup");
      return "auth/signup";
    }
    UserCreateRequest ucr = new UserCreateRequest();
    ucr.setEmail(signup.getEmail());
    ucr.setPassword(signup.getPassword());
    ucr.setFirstName(signup.getFirstName());
    ucr.setLastName(signup.getLastName());

    UserGetResponse createdUser = usersApi.createUser(ucr, request);

    return "redirect:/login";
  }
}
