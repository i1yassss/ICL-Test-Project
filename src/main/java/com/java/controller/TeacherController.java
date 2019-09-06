package com.java.controller;

import com.java.model.Teacher;
import com.java.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping(path = "/add")
    public String addNewTeacher (@RequestParam String surname, @RequestParam String name, @RequestParam String patronymic, Model model) {
        Teacher t = new Teacher(surname, name, patronymic);
        teacherRepository.save(t);
        Iterable<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("teachers", teachers);
        return "redirect:/teachers";
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id, Model model) {
        teacherRepository.deleteById(id);
        model.addAttribute("teachers", teacherRepository.findAll());
        return "redirect:/teachers";
    }

    @GetMapping
    public String teachers(Model model) {
        Iterable<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("teachers", teachers);
        return "teacher";
    }
}
