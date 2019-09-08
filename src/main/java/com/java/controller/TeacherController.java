package com.java.controller;

import com.java.form.TeacherForm;
import com.java.model.Teacher;
import com.java.repository.TeacherRepository;
import com.java.service.SubjectService;
import com.java.service.TeacherService;
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
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @PostMapping(path = "/add")
    public String addNewTeacher (TeacherForm teacherForm) {
        teacherService.save(teacherForm);
        return "redirect:/teachers";
    }

    @GetMapping("/add-teacher")
    public String showGroups(Model model) {
        return "add-teacher";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id, Model model) {
        teacherService.deleteById(id);
        //model.addAttribute("teachers", teacherService.findAll());
        return "redirect:/teachers";
    }

    @GetMapping
    public String teachers(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teacher";
    }
    @GetMapping("/teacherSubjects/{id}")
    public String getTeacherSubjects(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("currentTeacher", teacherService.findById(id));
        model.addAttribute("teacherSubjects", teacherService.getTeacherSubjects(id));
        model.addAttribute("allSubjects", subjectService.findAll());
        return "teacher-subjects";
    }

    @PostMapping("/joinSubjects/{id}")
    public String joinTeacherToSubject(TeacherForm teacherForm,@PathVariable("id") Integer id, Model model) {
        teacherService.joinToSubject(teacherForm, id);
        return "redirect:/teachers/teacherSubjects/{id}";
    }
}
