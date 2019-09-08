package com.java.service.impl;

import com.java.form.TeacherForm;
import com.java.model.Subject;
import com.java.model.Teacher;
import com.java.repository.SubjectRepository;
import com.java.repository.TeacherRepository;
import com.java.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Iterable<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void save(TeacherForm teacherForm) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherForm.getName());
        teacher.setSurname(teacherForm.getSurname());
        teacher.setPatronymic(teacherForm.getPatronymic());

        teacherRepository.save(teacher);
    }

    @Override
    public void joinToSubject(TeacherForm teacherForm, Integer id) {
        Teacher teacher = teacherRepository.findById(id).get();
        teacher.getSubject().add(subjectRepository.findById(teacherForm.getSubjectId()).get());
        teacherRepository.save(teacher);
    }

    @Override
    public Iterable<Subject> getTeacherSubjects(Integer id) {
        Teacher teacher = teacherRepository.findById(id).get();
        return subjectRepository.findAllByTeacher(teacher);
    }
}
