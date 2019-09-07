package com.java.service;

import com.java.form.StudentForm;
import com.java.model.Groups;
import com.java.model.Student;


public interface StudentService {

    Student joinGroup(Student student);
    void deleteById(Integer id);
    Iterable<Student> findAll();
    Iterable<Groups> showGroups();
    Student findById(Integer id);
    void update(StudentForm studentForm, Integer id);
    void save(StudentForm studentForm);
}
