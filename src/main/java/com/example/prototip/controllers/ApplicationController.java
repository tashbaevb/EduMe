package com.example.prototip.controllers;

import com.example.prototip.models.App;
import com.example.prototip.repo.AppRepository;
import com.example.prototip.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ApplicationController {
    @Autowired
    private AppRepository appRepository;
    @Value("${upload.folder}")
    String uploadPath;
    @Autowired
    private ImageService imageService;

    @GetMapping("/application_blog")
    public String application_blog(Model model) {
        Iterable<App> apps = appRepository.findAll();
        model.addAttribute("apps", apps);
        return ("blog_application");
    }

    @GetMapping("/application_blog/{id}")
    public String application_blog_more(@PathVariable(value = "id") long id, Model model) {
        if (!appRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<App> app = appRepository.findById(id);
        ArrayList<App> res = new ArrayList<>();
        app.ifPresent(res::add);
        model.addAttribute("app", res);
        return "blog_application_more";
    }

    @PostMapping("/")
    public String createApplication(
            @RequestParam String title,
            @RequestParam MultipartFile image,
            @RequestParam String anons,
            @RequestParam String full_text) throws IOException {
        App app = App.builder()
                .title(title)
                .anons(anons)
                .fullText(full_text)
                .build();
        app.setPhotoUrl(imageService.saveToTheFileSystem(image));
        appRepository.save(app);
        return "redirect:/blog";
    }

    @GetMapping("/app/add")
    public String appAdd() {
        return "app_add";
    }

    @GetMapping("/application_blog/{id}/edit")
    public String appEdit(@PathVariable(value = "id") long id, Model model) {
        if (!appRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<App> app = appRepository.findById(id);
        ArrayList<App> res = new ArrayList<>();
        app.ifPresent(res::add);
        model.addAttribute("app", res);
        return "app_edit";
    }

    @GetMapping("he")
    public String he() {
        return "hello";
    }

    @PostMapping("/application_blog/{id}/edit")
    public String appUpdate(@PathVariable(value = "id") long id, @RequestParam String title,
                            @RequestParam String anons,
                            @RequestParam String fullText) {
        App app = appRepository.findById(id).orElseThrow();
        app.setTitle(title);
        app.setAnons(anons);
        app.setFullText(fullText);
        appRepository.save(app);
        return "redirect:/blog";
    }

    @PostMapping("/application_blog/{id}/remove")
    public String appDelete(@PathVariable(value = "id") long id) {
        App app = appRepository.findById(id).orElseThrow();
        appRepository.delete(app);
        return "redirect:/application_blog";
    }
}
