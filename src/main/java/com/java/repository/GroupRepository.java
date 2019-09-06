package com.java.repository;

import com.java.model.Groups;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Groups, Integer> {

}
