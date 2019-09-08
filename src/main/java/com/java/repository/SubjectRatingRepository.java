package com.java.repository;


import com.java.model.Student;
import com.java.model.Subject;
import com.java.model.SubjectRating;
import com.java.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRatingRepository extends CrudRepository<SubjectRating, Integer> {
    List<SubjectRating> findAllByStudent(Student student);
    SubjectRating findByStudent(Student student);
    SubjectRating findBySubject(Subject subject);
}
