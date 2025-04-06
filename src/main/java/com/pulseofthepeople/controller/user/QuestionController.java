package com.pulseofthepeople.controller.user;

import com.pulseofthepeople.dto.QuestionForm;
import com.pulseofthepeople.entity.Question;
import com.pulseofthepeople.entity.Tag;
import com.pulseofthepeople.entity.User;
import com.pulseofthepeople.repository.QuestionRepository;
import com.pulseofthepeople.repository.TagRepository;
import com.pulseofthepeople.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/question")
public class QuestionController {

    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private TagRepository tagRepository;

    public QuestionController(TagRepository tagRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/submit-question")
    public String postQuestion(Model model, @Valid @ModelAttribute QuestionForm questionForm,
                               BindingResult theBindingResult, HttpSession httpSession) {

        if (theBindingResult.hasErrors()) {
            System.out.println(theBindingResult.toString());
            return "/user/ask-question";
        }

        User user = userRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName());

        // Create and populate the Question entity with form data
        Question newQuestion = new Question();
        newQuestion.setUser(user);
        newQuestion.setTitle(questionForm.getTitle());
        newQuestion.setDetails(questionForm.getDetails());
        newQuestion.setOfficial(questionForm.getOfficial());
        newQuestion.setRegion(questionForm.getRegion());

        // Save the question first
        questionRepository.save(newQuestion);
        System.out.println(questionForm.getTag());
        // Process tag if it's not empty
        if (questionForm.getTag() != null && !questionForm.getTag().trim().isEmpty()) {
            // Normalize the tag (trim and convert to lowercase)
            String normalizedTag = questionForm.getTag().trim().toLowerCase();

            // Check if tag already exists
            Tag existingTag = tagRepository.findByValueIgnoreCase(normalizedTag);

            if (existingTag == null) {
                // Tag doesn't exist, create a new one
                Tag newTag = new Tag(normalizedTag);
                newTag.addQuestion(newQuestion);
                tagRepository.save(newTag);
                System.out.println("tag saved");

            } else {
                // Tag exists, add the question to it
                existingTag.addQuestion(newQuestion);
                tagRepository.save(existingTag);
                System.out.println("tag saved");

            }
        }

        System.out.println("Question saved for user: " + user.getEmail());

        return "redirect:/user/ask";
    }


}
