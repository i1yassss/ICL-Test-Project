package com.java.service.impl;

import com.java.form.StudentForm;
import com.java.model.*;
import com.java.repository.*;
import com.java.service.GroupService;
import com.java.service.StudentService;
import com.java.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectRatingRepository subjectRatingRepository;

    @Autowired
    private TeacherRepository teacherRepository;

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
        return groupRepository.findAll();
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
        student.setGroup(group);

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
        student.setGroup(group);

        studentRepository.save(student);
    }

    @Override
    public void joinToSubject(StudentForm studentForm, Integer id) {
        Student student = studentRepository.findById(id).get();
        SubjectRating subjectRating = new SubjectRating();
        subjectRating.setStudent(student);
        subjectRating.setSubject(subjectRepository.findById(studentForm.getSubjectId()).get());
        subjectRatingRepository.save(subjectRating);
    }

    @Override
    public Iterable<Teacher> findTeachersBySubjectRatingId(Integer subjectRatingId) {
        SubjectRating subjectRating = subjectRatingRepository.findById(subjectRatingId).get();
        Subject subject = subjectRepository.findById(subjectRating.getSubject().getId()).get();
        return subject.getTeacher();
    }

    @Override
    public Student findBySubjectId(Integer id) {
        SubjectRating subjectRating = subjectRatingRepository.findById(id).get();
        Student student = studentRepository.findById(subjectRating.getStudent().getId()).get();
        return student;
    }

/*

    @Override
    public Iterable<Subject> getStudentsSubjects(Integer id) {
        Student student = studentRepository.findById(id).get();
        return subjectRatingRepository.findAllByStudent(student);
    }
*/


}
