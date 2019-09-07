package com.java.service.impl;

import com.java.form.StudentForm;
import com.java.model.Groups;
import com.java.model.Student;
import com.java.repository.GroupRepository;
import com.java.repository.StudentRepository;
import com.java.service.GroupService;
import com.java.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Student joinGroup(Student student) {
        return null;

    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Iterable<Groups> showGroups() {
        return  groupRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void update(StudentForm studentForm, Integer id) {
        Student student = studentRepository.findById(id).get();
        student.setName(studentForm.getName());
        student.setSurname(studentForm.getSurname());
        student.setPatronymic(studentForm.getPatronymic());
        student.setBirthday(studentForm.getBirthday());

        Groups group = groupService.findById(studentForm.getGroupId());
        student.setGroups(group);

        studentRepository.save(student);
    }

    @Override
    public void save(StudentForm studentForm) {
        Student student = new Student();
        student.setName(studentForm.getName());
        student.setSurname(studentForm.getSurname());
        student.setPatronymic(studentForm.getPatronymic());
        student.setBirthday(studentForm.getBirthday());

        Groups group = groupService.findById(studentForm.getGroupId());
        student.setGroups(group);

        studentRepository.save(student);
    }

}
