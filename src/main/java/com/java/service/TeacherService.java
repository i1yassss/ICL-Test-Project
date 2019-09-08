package com.java.service;

import com.java.form.TeacherForm;
import com.java.model.Subject;
import com.java.model.Teacher;

import java.util.List;

public interface TeacherService {

    Iterable<Teacher> findAll();
    Teacher findById(Integer id);
    void deleteById(Integer id);
    void save(TeacherForm teacherForm);
    void joinToSubject(TeacherForm teacherForm, Integer id);
    Iterable<Subject> getTeacherSubjects(Integer id);
}
