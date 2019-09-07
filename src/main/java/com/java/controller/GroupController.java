package com.java.controller;

import com.java.form.GroupForm;
import com.java.model.Groups;
import com.java.repository.GroupRepository;
import com.java.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping(path = "/add")
    public String addNewGroup (GroupForm groupForm, Model model) {
        groupService.save(groupForm);
        model.addAttribute("groups", groupService.findAll());
        return "redirect:/groups";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id, Model model) {
        groupService.deleteGroupById(id);
        model.addAttribute("groups", groupService.findAll());
        return "redirect:/groups";
    }

    @GetMapping
    public String groups(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "group";
    }

}
