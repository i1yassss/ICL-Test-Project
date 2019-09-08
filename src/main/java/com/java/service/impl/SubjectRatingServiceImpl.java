package com.java.service.impl;

import com.java.form.SubjectRatingForm;
import com.java.model.Student;
import com.java.model.Subject;
import com.java.model.SubjectRating;
import com.java.model.Teacher;
import com.java.repository.StudentRepository;
import com.java.repository.SubjectRatingRepository;
import com.java.repository.SubjectRepository;
import com.java.repository.TeacherRepository;
import com.java.service.SubjectRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectRatingServiceImpl implements SubjectRatingService {

    @Autowired
    private SubjectRatingRepository subjectRatingRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectRating findById(Integer id) {
        return subjectRatingRepository.findById(id).get();
    }

    @Override
    public List<SubjectRating> findStudentsSubjectsByStudentId(Integer id) {
        Student student = studentRepository.findById(id).get();
        return subjectRatingRepository.findAllByStudent(student);
    }


    @Override
    public void addTeacherToSubjectRatingTable(SubjectRatingForm subjectRatingForm, Integer subjectRatingId) {
        SubjectRating subjectRating = subjectRatingRepository.findById(subjectRatingId).get();
        subjectRating.setTeacher(subjectRatingForm.getTeacher());
        subjectRatingRepository.save(subjectRating);
    }

    @Override
    public void editRating(SubjectRatingForm subjectRatingForm, Integer id) {
        SubjectRating subjectRating = subjectRatingRepository.findById(id).get();
        subjectRating.setRating(subjectRatingForm.getRating());
        subjectRatingRepository.save(subjectRating);
    }

}
