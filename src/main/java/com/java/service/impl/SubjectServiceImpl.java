package com.java.service.impl;

import com.java.form.SubjectForm;
import com.java.model.Subject;
import com.java.repository.SubjectRepository;
import com.java.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService{
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Override
    public void save(SubjectForm subjectForm) {
        Subject subject = new Subject();
        subject.setTitle(subjectForm.getTitle());
        subjectRepository.save(subject);
        
    }

    @Override
    public Subject findById(Integer id) {
        return subjectRepository.findById(id).get();
    }

    @Override
    public Iterable<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void deleteSubjectById(Integer id) {
        subjectRepository.deleteById(id);
    }
}
