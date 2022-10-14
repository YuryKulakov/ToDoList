package com.todo.todolist.controllers;

import com.todo.todolist.models.UserRepresentation;
import com.todo.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    private String loginPage() {
        return "login.html";
    }

    @GetMapping("/register")
    private String registerPage(Model model){
        model.addAttribute("user",new UserRepresentation());
        return "registration.html";
    }

    @PostMapping("/register")
    private String registerPage(@ModelAttribute ("user") @Validated UserRepresentation user, BindingResult result){
        if(result.hasErrors()){
            return "registration.html";
        }
        userService.createUser(user);
        return "redirect:/login";
    }
}
