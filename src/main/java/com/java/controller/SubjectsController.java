package com.java.controller;

import com.java.model.Subjects;
import com.java.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectsController {

    @Autowired
    private SubjectsRepository subjectsRepository;

    @PostMapping(path = "/add")
    public String addNewSubject (@RequestParam String title, Model model) {
        Subjects s = new Subjects(title);
        subjectsRepository.save(s);
        Iterable<Subjects> subjects = subjectsRepository.findAll();
        model.addAttribute("subjects", subjects);
        return "redirect:/subjects";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteSubject(@PathVariable("id") Integer id, Model model) {
        subjectsRepository.deleteById(id);
        model.addAttribute("subjects", subjectsRepository.findAll());
        return "redirect:/subjects";
    }

    @GetMapping
    public String subjects(Model model) {
        Iterable<Subjects> subjects = subjectsRepository.findAll();
        model.addAttribute("subjects", subjects);
        return "subject";
    }

}
