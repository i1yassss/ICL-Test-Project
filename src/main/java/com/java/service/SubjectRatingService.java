package com.java.service;

import com.java.form.SubjectRatingForm;
import com.java.model.Student;
import com.java.model.SubjectRating;

import java.util.List;

public interface SubjectRatingService {

    SubjectRating findById(Integer id);
    List<SubjectRating> findStudentsSubjectsByStudentId(Integer id);
    void addTeacherToSubjectRatingTable(SubjectRatingForm subjectRatingForm, Integer subjectRatingId);
    void editRating(SubjectRatingForm subjectRatingForm, Integer id);

}
