package com.java.service;

import com.java.form.SubjectForm;
import com.java.model.Subject;

import java.util.List;

public interface SubjectService {

    void save(SubjectForm subjectForm);
    Subject findById(Integer id);
    Iterable<Subject> findAll();
    List<Subject> allSubjects();
    void deleteSubjectById(Integer id);


}
