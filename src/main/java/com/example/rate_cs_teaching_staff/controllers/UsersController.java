package com.example.rate_cs_teaching_staff.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.rate_cs_teaching_staff.models.Staff;
import com.example.rate_cs_teaching_staff.models.UsersRepository;

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
  
}
