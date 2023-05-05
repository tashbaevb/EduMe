package com.example.prototip.controllers;

import com.example.prototip.models.Post;
import com.example.prototip.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class StudentsController {
    @GetMapping("/students")
    public String student(Model model){
        return ("students");
    }



    @Autowired
    private PostRepository postRepository;

    @GetMapping("/students/uni")
    public String uni(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "uni";
    }


    @GetMapping("/students/uni/{id}")
    public String uniMore(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/students/uni";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "uni_more";


    }
}
