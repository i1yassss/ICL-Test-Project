package com.java.repository;


import com.java.model.Subject;
import com.java.model.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Teacher findBySubject(Subject subject);
}
