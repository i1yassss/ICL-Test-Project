package com.java.service;

import com.java.form.SubjectForm;
import com.java.model.Subject;

public interface SubjectService {

    void save(SubjectForm subjectForm);
    Subject findById(Integer id);
    Iterable<Subject> findAll();
    void deleteSubjectById(Integer id);

}
