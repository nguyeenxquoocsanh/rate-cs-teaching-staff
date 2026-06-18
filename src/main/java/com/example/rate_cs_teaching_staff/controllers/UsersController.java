package com.example.rate_cs_teaching_staff.controllers;

// import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.rate_cs_teaching_staff.models.Staff;
import com.example.rate_cs_teaching_staff.models.UsersRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



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


    //Modifying Users
    @GetMapping("/users/modify/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model){
        Staff staff = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        model.addAttribute("staff", staff);
        return "users/modify";
    }

    @PostMapping("users/modified/{id}")
    public String modifiedStaff(@PathVariable("id") int id, @RequestParam Map<String, String> modifiedStaff){
        System.out.println("Updating Staff with ID: " + id);

        String name = modifiedStaff.get("name");
        String email = modifiedStaff.get("email");
        String roleType = modifiedStaff.get("roleType");
        int clarity = Integer.parseInt(modifiedStaff.get("clarity"));
        int niceness = Integer.parseInt(modifiedStaff.get("niceness"));
        int knowledgeableScore = Integer.parseInt(modifiedStaff.get("knowledgeableScore"));
        String comment = modifiedStaff.get("comment");

        userRepo.save(new Staff(id, name, email, roleType, clarity, niceness, knowledgeableScore, comment));
        return "users/modifiedStaff";

    }
}
