package com.java.service;

import com.java.form.TeacherForm;
import com.java.model.Teacher;

public interface TeacherService {

    Iterable<Teacher> findAll();
    Teacher findById(Integer id);
    void deleteById(Integer id);
    void save(TeacherForm teacherForm);
}
