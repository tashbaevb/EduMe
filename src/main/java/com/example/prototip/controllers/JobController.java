package com.example.prototip.controllers;

import com.example.prototip.models.App;
import com.example.prototip.models.Job;
import com.example.prototip.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/job")
    public String job(Model model,@RequestParam(required = false) String query, @RequestParam(required = false) Integer minPrice) {
        Iterable<Job> jobs;
        if (minPrice != null && query != null) {
            jobs = jobRepository.findByTitleOrContentLike(query, minPrice);
        } else {
            jobs = jobRepository.findAll();
        }
        model.addAttribute("jobs", jobs);
        return "job";
    }

    @GetMapping("/job/{id}")
    public String jobMore(@PathVariable(value = "id") long id, Model model) {
        if (!jobRepository.existsById(id)) {
            return "redirect:/job";
        }
        Optional<Job> job = jobRepository.findById(id);
        ArrayList<Job> res = new ArrayList<>();
        job.ifPresent(res::add);
        model.addAttribute("job", res);
        return "job_more";
    }
}
