package com.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller // This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /api (after Application path)
public class UserController {
  @Autowired
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  private String passwordEncode(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  @PostMapping(path="/signup") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String username,
      @RequestParam String email, @RequestParam String password, @RequestParam Boolean admin) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    User n = new User();
    n.setUsername(username);
    n.setEmail(email);
    n.setPassword(passwordEncode(password));
    n.setAdmin(admin);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }
}