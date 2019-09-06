package com.java.repository;

import com.java.model.Subjects;
import org.springframework.data.repository.CrudRepository;

public interface SubjectsRepository extends CrudRepository<Subjects, Integer> {
}
