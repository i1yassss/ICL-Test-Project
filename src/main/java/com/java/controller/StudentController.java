package com.java.controller;

import com.java.model.Groups;
import com.java.model.Student;
import com.java.repository.GroupRepository;
import com.java.repository.StudentRepository;
import com.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path="/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/add")
    public String addNewStudent (@RequestParam String surname, @RequestParam String name, @RequestParam String patronymic, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday, Model model) {
        Student st = new Student(surname, name, patronymic, birthday);
        studentRepository.save(st);
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "redirect:/students";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, Model model) {
        studentRepository.deleteById(id);
        model.addAttribute("students", studentRepository.findAll());
        return "redirect:/students";
    }

    @GetMapping("/add-student")
    public String addForm(Model model) {
        return "add-student";
    }

    @GetMapping
    public String students(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        Iterable<Groups> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return "student";
    }
}