package com.java.controller;

import com.java.form.SubjectForm;
import com.java.model.Subject;
import com.java.repository.SubjectRepository;
import com.java.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping(path = "/add")
    public String addNewSubject (SubjectForm subjectForm, Model model) {
        subjectService.save(subjectForm);
        model.addAttribute("subjects", subjectService.findAll());
        return "redirect:/subjects";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteSubject(@PathVariable("id") Integer id, Model model) {
        subjectService.deleteSubjectById(id);
        model.addAttribute("subjects", subjectService.findAll());
        return "redirect:/subjects";
    }

    @GetMapping
    public String subjects(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subject";
    }

}
