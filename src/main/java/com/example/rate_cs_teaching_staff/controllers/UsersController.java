package com.example.rate_cs_teaching_staff.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.rate_cs_teaching_staff.models.Staff;

@Controller
public class UsersController {
    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Getting all users");
        List<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff("1", "John Doe", "john.doe@example.com", "TA", 5, 4, 5, "Great instructor!"));
        staffList.add(new Staff("2", "Jacob Zuckerberg", "jacob.zuckerberg@example.com", "TA", 5, 4, 5, "Great instructor!"));
        staffList.add(new Staff("3", "Jane Smith", "jane.smith@example.com", "Prof", 5, 4, 5, "Great instructor!"));
        staffList.add(new Staff("4", "Sarah Chan", "sarah.chan@example.com", "TA", 5, 4, 5, "Great instructor!"));
        staffList.add(new Staff("5", "Ben Barn", "ben.barn@example.com", "Instructor", 5, 4, 5, "Great instructor!"));
        //End of database call
        model.addAttribute("staff", staffList);
        return "users/showAll";
    }
  
}
