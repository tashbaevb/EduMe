package com.example.prototip.controllers;

import com.example.prototip.models.Job;
import com.example.prototip.repo.JobRepository;
import com.example.prototip.services.ImageService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BlogJobController {
    @Autowired
    private JobRepository jobRepository;
    @Value("${upload.folder}")
    String uploadPath;
    @Autowired
    private ImageService imageService;

    @GetMapping("/job_blog")
    public String job_blog(Model model) {
        Iterable<Job> jobs = jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        return ("blog_job");
    }

    @GetMapping("/job_blog/{id}")
    public String job_blog_more(@PathVariable(value = "id") long id, Model model) {
        if (!jobRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Job> job = jobRepository.findById(id);
        ArrayList<Job> res = new ArrayList<>();
        job.ifPresent(res::add);
        model.addAttribute("job", res);
        return "blog_job_more";
    }

    @GetMapping("/job_blog/add")
    public String jobAdd() {
        return "job_add";
    }

    @PostMapping("/job_blog/add")
    public String JobAdd(
            @RequestParam String title,
            @RequestParam MultipartFile image,
            @RequestParam String anons,
            @RequestParam String full_text,
            @RequestParam int price) throws IOException {
        String imageUrl = imageService.saveToTheFileSystem(image);
        Job job = new Job(title, anons, full_text, price);
        job.setPhotoUrl(imageUrl);
        jobRepository.save((job));
        return "redirect:/blog";
    }

    @GetMapping("/job_blog/{id}/edit")
    public String jobEdit(@PathVariable(value = "id") long id, Model model) {
        if (!jobRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Job> job = jobRepository.findById(id);
        ArrayList<Job> res = new ArrayList<>();
        job.ifPresent(res::add);
        model.addAttribute("job", res);
        return "job_edit";
    }

    @PostMapping("/job_blog/{id}/edit")
    public String jobUpdate(@PathVariable(value = "id") long id, @RequestParam String title,
                            @RequestParam String anons,
                            @RequestParam String full_text) {
        Job job = jobRepository.findById(id).orElseThrow();
        job.setTitle(title);
        job.setAnons(anons);
        job.setFull_text(full_text);
        jobRepository.save(job);
        return "redirect:/blog";
    }

    @PostMapping("/job_blog/{id}/remove")
    public String jobDelete(@PathVariable(value = "id") long id) {
        Job job = jobRepository.findById(id).orElseThrow();
        jobRepository.delete(job);
        return "redirect:/blog";
    }
}
