package com.java.service;

import com.java.form.GroupForm;
import com.java.model.Groups;

public interface GroupService {

    Groups findById(Integer groupId);
    Iterable<Groups> findAll();
    void save(GroupForm groupForm);
    void deleteGroupById(Integer id);

}
