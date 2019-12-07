package com.sda.project.controllers;

import com.sda.project.entities.User;
import com.sda.project.entities.UserGender;
import com.sda.project.entities.UserStatus;
import com.sda.project.repositories.UsersRepository;
import com.sda.project.services.PostService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @GetMapping({"/", "/index"})
    public String home(Model model) {
        //model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("genders", UserGender.values());
        return "user-add";
    }

    @PostMapping("/adduser")
    public String create(@Valid User newUser, Model model, BindingResult result, HttpSession session) {

        User savedUser = userService.createUser(newUser);
        if(result.hasErrors()) {
            return "user-add";
        }
        else {
            session.setAttribute("user", savedUser);
            return "user-profile";
        }

    }

    @PostMapping("/login")
    public String login(Model model, User user, HttpSession session){
        User valUser = userService.validateUser(user);
        if(valUser == null) {
            return  "index";
        }
        else {
            session.setAttribute("user", valUser);
            model.addAttribute("posts", postService.getPostsByUserId(valUser.getUserId()));
            return "user-profile";
        }

    }

    @GetMapping("/delete")
    public String delete(HttpSession session){
        User user = (User) session.getAttribute("user");
        userService.deleteUser(user);
        return "redirect:index";
    }

    @GetMapping("feed")
    public String goToFeed(){
        return "feed";
    }

    @GetMapping("list-of-friends")
    public String goToFriendList(){
        return "friend-list";
    }

    @GetMapping("list-of-users")
    public String goToListOfUsers(Model model){
        model.addAttribute("users", userService.getActiveUsers());
        return "user-list";
    }
}
