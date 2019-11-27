package com.sda.project.controllers;

import com.sda.project.entities.User;
import com.sda.project.entities.UserStatus;
import com.sda.project.repositories.UsersRepository;
import com.sda.project.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String home(Model model) {
        //model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/adduser")
    public String create(@Valid User newUser, Model model, BindingResult result) {

        User savedUser = userService.createUser(newUser);
        if(result.hasErrors()) {
            return "user-add";
        }
        else {
            model.addAttribute("user", savedUser);
            return "user-profile";
        }

    }

    @PostMapping("/login")
    public String login(Model model, User user){
        //TODO write a method which verifies credentials and logs in
        User valUser = userService.validateUser(user);
        if(valUser == null) {
            return  "index";
        }
        else {
            model.addAttribute("user", valUser);
            return "user-profile";
        }

    }
}
