package com.java.service.impl;

import com.java.dto.StudentRating;
import com.java.form.SubjectRatingForm;
import com.java.model.Student;
import com.java.model.SubjectRating;
import com.java.repository.StudentRepository;
import com.java.repository.SubjectRatingRepository;
import com.java.repository.SubjectRepository;
import com.java.repository.TeacherRepository;
import com.java.service.SubjectRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public Iterable<SubjectRating> findStudentsSubjectsByStudentId(Integer id) {
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

    @Override
    public List<StudentRating> getStudentsAverageRatings() {
        Iterable<Student> students = studentRepository.findAll();
        List<StudentRating> studentRating = new ArrayList<>();

        for (Student student : students) {
            List<SubjectRating> subjectRatings = subjectRatingRepository.findAllByStudent(student);

            double average = subjectRatings.stream()
                    .mapToDouble(SubjectRating::getRating)
                    .average()
                    .orElse(0.0);

            studentRating.add(new StudentRating(student, average));
        }

        return studentRating;
    }

    @Override
    public List<StudentRating> worstStudentsByRating(int limit) {
        List<StudentRating> studentRatings = getStudentsAverageRatings();
        return studentRatings.stream()
                .sorted(Comparator.comparingDouble(StudentRating::getAverageRating))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentRating> bestStudentsByRating(int limit) {
        List<StudentRating> studentRatings = getStudentsAverageRatings();
        return studentRatings.stream()
                .sorted(Comparator.comparingDouble(StudentRating::getAverageRating)
                        .reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

}
