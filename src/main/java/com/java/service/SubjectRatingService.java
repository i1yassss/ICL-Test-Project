package com.java.service;

import com.java.dto.StudentRating;
import com.java.form.SubjectRatingForm;
import com.java.model.Student;
import com.java.model.SubjectRating;

import java.util.List;
import java.util.Map;

public interface SubjectRatingService {

    SubjectRating findById(Integer id);

    Iterable<SubjectRating> findStudentsSubjectsByStudentId(Integer id);

    void addTeacherToSubjectRatingTable(SubjectRatingForm subjectRatingForm, Integer subjectRatingId);

    void editRating(SubjectRatingForm subjectRatingForm, Integer id);

    Iterable<StudentRating> getStudentsAverageRatings();

    List<StudentRating> bestStudentsByRating(int limit);

    List<StudentRating> worstStudentsByRating(int limit);

}
