package com.java.controller;

import com.java.form.StudentForm;
import com.java.model.Groups;
import com.java.model.Student;
import com.java.repository.GroupRepository;
import com.java.repository.StudentRepository;
import com.java.service.GroupService;
import com.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/add")
    public String addNewStudent(StudentForm studentForm, Model model) {
        studentService.save(studentForm);
        return "redirect:/students";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, Model model) {
        studentService.deleteById(id);
        model.addAttribute("students", studentService.findAll());
        return "redirect:/students";
    }

    @GetMapping("/add-student")
    public String showGroups(Model model) {
        model.addAttribute("groups", studentService.showGroups());
        return "add-student";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("student", studentService.findById(id));
        return "update-student";
    }


    @PostMapping("/updateStudent/{id}")
    public String update(@PathVariable("id") Integer id, Model model, StudentForm studentForm) {
        studentService.update(studentForm, id);
        return "redirect:/students";
    }

    @GetMapping
    public String students(Model model) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        return "student";
    }
}