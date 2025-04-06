package com.pulseofthepeople.controller.user;

import com.pulseofthepeople.dto.QuestionForm;
import com.pulseofthepeople.dto.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/ask")
    public String getRegistrationPage(Model model){
        model.addAttribute("QuestionDTO", new QuestionForm());
        return "/user/ask-question";
    }

    @GetMapping("/dashboard")
    public String getDashboardPage(Model model){
        model.addAttribute("userForm", new UserForm());
        return "/user/dashboard";
    }

    @GetMapping("/my-questions")
    public String getMyQuestionsPage(Model model){
        model.addAttribute("userForm", new UserForm());
        return "/user/my-question";
    }

    @GetMapping("/browse-all")
    public String getBrowseAllPage(Model model){
        model.addAttribute("userForm", new UserForm());
        return "/user/browse-all";
    }

    @GetMapping("/trending")
    public String getTrendingPage(Model model){
        model.addAttribute("userForm", new UserForm());
        return "/user/trending";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model){
        model.addAttribute("userForm", new UserForm());
        return "/user/profile";
    }
}
