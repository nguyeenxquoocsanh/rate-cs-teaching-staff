package com.example.rate_cs_teaching_staff.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.rate_cs_teaching_staff.models.Staff;
import com.example.rate_cs_teaching_staff.models.UsersRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsersController {
    @Autowired
    private UsersRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Getting all users");
        // Database call to get all users
        List<Staff> staffs = userRepo.findAll();
        //End of database call
        model.addAttribute("staff", staffs);
        return "users/showAll";
    }
    @PostMapping("/users/add")
    public String addStaff(@RequestParam Map<String, String> newStaff, HttpServletResponse response) {
        System.out.println("Adding Staff");
        String newName = newStaff.get("name");
        String newEmail = newStaff.get("email");
        String newRoleType = newStaff.get("roleType");
        int newClarity = Integer.parseInt(newStaff.get("clarity"));
        int newNiceness = Integer.parseInt(newStaff.get("niceness"));
        int newKnowledgeableScore = Integer.parseInt(newStaff.get("knowledgeableScore"));
        String newComment = newStaff.get("comment");
        userRepo.save(new Staff(0,newName, newEmail, newRoleType, newClarity, newNiceness, newKnowledgeableScore, newComment));
        response.setStatus(201);
        return "users/addedStaff";
    }
  
}
