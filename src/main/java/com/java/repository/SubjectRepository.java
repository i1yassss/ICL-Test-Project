package com.java.repository;

import com.java.model.Student;
import com.java.model.Subject;
import com.java.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
    List<Subject> findAllByTeacher(Teacher teacher);
    Subject findByTeacher(Teacher teacher);
}
