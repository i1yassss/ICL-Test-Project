package com.java.controller;

import com.java.model.Groups;
import com.java.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping(path = "/add")
    public String addNewGroup (@RequestParam String groupname,  Model model) {
        Groups g = new Groups(groupname);
        groupRepository.save(g);
        Iterable<Groups> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return "redirect:/groups";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id, Model model) {
        groupRepository.deleteById(id);
        model.addAttribute("groups", groupRepository.findAll());
        return "redirect:/groups";
    }

    @GetMapping
    public String groups(Model model) {
        Iterable<Groups> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return "group";
    }

}
