package com.pulseofthepeople.controller;

import com.pulseofthepeople.dto.UserForm;
import com.pulseofthepeople.helper.Message;
import com.pulseofthepeople.helper.MessageType;
import com.pulseofthepeople.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {


    private UserService userService;

    public PageController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String defaultPage(){
        return "redirect:home";
    }

    @GetMapping("home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("userForm", new UserForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult theBindingResult, HttpSession httpSession) {

        // here you can process the form data
        if(theBindingResult.hasErrors()){
            System.out.println(theBindingResult.toString());
            theBindingResult.toString();
            return "/registration";
        }
        userService.saveUser(userForm);

        // add message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        httpSession.setAttribute("message", message);
//        System.out.println("UserForm data: " + userForm.toString());
        return "redirect:/registration";
    }

}
