package com.java.service.impl;

import com.java.model.Groups;
import com.java.model.Student;
import com.java.repository.GroupRepository;
import com.java.repository.StudentRepository;
import com.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student joinGroup(Student student) {
        return null;
    }

}
