package com.example.prototip.controllers;

import com.example.prototip.models.Feedback;
import com.example.prototip.repo.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Feedback> feedbacks = feedbackRepository.findAll();
        model.addAttribute("feedbacks", feedbacks);
        return "home";
    }

    @PostMapping("/feedback")
    public String createFeed(@RequestParam String title, @RequestParam String full_text) {
        Feedback feedback = Feedback.builder()
                .title(title).fullText(full_text).build();
        feedbackRepository.save(feedback);
        return "redirect:/";
    }
}