package com.example.prototip.controllers;

import com.example.prototip.models.App;
import com.example.prototip.models.Post;
import com.example.prototip.repo.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationStudController {

    @Autowired
    private AppRepository appRepository;

    @GetMapping("/")
    public String home(Model model){
        Iterable<App> apps = appRepository.findAll();
        model.addAttribute("apps", apps);
        return "home";
    }
    @GetMapping("/students/app/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<App> searchResults = appRepository.findByTitleOrContentLike(query);
        model.addAttribute("apps", searchResults);
        return "application";
    }

    @GetMapping("/students/apps")
    public String apps(Model model) {
        Iterable<App> apps = appRepository.findAll();
        model.addAttribute("apps", apps);
        return "application";
    }

    @GetMapping("/students/apps/{id}")
    public String appsMore(@PathVariable(value = "id") long id, Model model) {
        if (!appRepository.existsById(id)) {
            return "redirect:/students/apps";
        }
        Optional<App> app = appRepository.findById(id);
        ArrayList<App> res = new ArrayList<>();
        app.ifPresent(res::add);
        model.addAttribute("app", res);
        return "app_more";
    }
}
