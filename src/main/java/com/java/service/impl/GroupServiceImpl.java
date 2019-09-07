package com.java.service.impl;

        import com.java.form.GroupForm;
        import com.java.model.Groups;
        import com.java.repository.GroupRepository;
        import com.java.service.GroupService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Groups findById(Integer groupId) {
        return groupRepository.findById(groupId).get();
    }

    @Override
    public Iterable<Groups> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void save(GroupForm groupForm) {
        Groups group = new Groups();
        group.setName(groupForm.getGroupname());
        groupRepository.save(group);
    }

    @Override
    public void deleteGroupById(Integer id) {
        groupRepository.deleteById(id);
    }
}
