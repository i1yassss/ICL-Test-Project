package com.java.controller;

import com.java.dto.StudentRating;
import com.java.form.StudentForm;
import com.java.form.SubjectRatingForm;
import com.java.model.Student;
import com.java.service.GroupService;
import com.java.service.StudentService;
import com.java.service.SubjectRatingService;
import com.java.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRatingService subjectRatingService;

    @PostMapping(path = "/add")
    public String addNewStudent(StudentForm studentForm, Model model) {
        studentService.save(studentForm);
        return "redirect:/students";
    }

    @GetMapping(value = "/delete/{id}")
    @DeleteMapping
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

    @GetMapping("/studentSubjects/{id}")
    public String getStudentSubjects(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("currentStudent", studentService.findById(id));
        model.addAttribute("studentSubjects", subjectRatingService.findStudentsSubjectsByStudentId(id));
        model.addAttribute("allSubjects", subjectService.findAll());
        return "student-subjects";
    }

    @PostMapping("/joinSubjects/{id}")
    public String joinStudentToSubject(StudentForm studentForm, @PathVariable("id") Integer id, Model model) {
        studentService.joinToSubject(studentForm, id);
        return "redirect:/students/studentSubjects/{id}";
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

    @GetMapping("/appointTeacher/{id}")
    public String appointTeacher(@PathVariable("id") Integer subjectRatingId, Model model){
        model.addAttribute("teachers", studentService.findTeachersBySubjectRatingId(subjectRatingId));
        //model.addAttribute("currentStudent", studentService.findBySubjectId(subjectRatingId));
        model.addAttribute("subjectRatingId", subjectRatingService.findById(subjectRatingId));

        return "appoint-teacher";
    }

    @PostMapping("/appointTeacher/{id}")
    public String appointTeacherToSubjectRating(SubjectRatingForm subjectRatingForm, @PathVariable("id") Integer id, Model model){
        subjectRatingService.addTeacherToSubjectRatingTable(subjectRatingForm, id);
        return "redirect:/students/";
    }

    @PostMapping("/editRating/{id}")
    public String editRating(SubjectRatingForm subjectRatingForm, @PathVariable("id") Integer id, Model model){
        subjectRatingService.editRating(subjectRatingForm, id);
        return "redirect:/students/";
    }

    @GetMapping("/editRatingPage/{id}")
    public String editRatingPage(@PathVariable("id") Integer id, Model model){
        model.addAttribute("rating", subjectRatingService.findById(id));
        return "edit-rating";
    }

    @GetMapping("/rating")
    public String getRating(Model model){
        Iterable<StudentRating> studentRatings = subjectRatingService.getStudentsAverageRatings();
        model.addAttribute("studentsRating", studentRatings);
        return "rating";
    }

    @GetMapping("/bestRatingStudents")
    public String getBestRatingStudents(Model model){
        List<StudentRating> bestRatings = subjectRatingService.bestStudentsByRating(3);
        model.addAttribute("bestRatings", bestRatings);
        return "best-students-rating";
    }
    @GetMapping("/worstRatingStudents")
    public String getWorstRatingStudents(Model model){
        List<StudentRating> worstRatings = subjectRatingService.worstStudentsByRating(3);
        model.addAttribute("worstRatings", worstRatings);
        return "worst-students-rating";
    }
}