package com.example.rate_cs_teaching_staff.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.rate_cs_teaching_staff.models.Staff;
import com.example.rate_cs_teaching_staff.models.UsersRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsersController {
    
    @Autowired
    private UsersRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Getting all users");
        List<Staff> staffs = userRepo.findAll();
        model.addAttribute("staff", staffs);
        return "users/showAll";
    }

    @GetMapping("/users/view/{id}")
    public String viewStaffDetail(@PathVariable("id") int id, Model model) {
        Staff staff = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        model.addAttribute("staff", staff);
        return "users/detail"; 
    }

    @PostMapping("/users/delete/{id}")
    public String deleteStaff(@PathVariable("id") int id) {
        Staff staff = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        userRepo.delete(staff);
        return "redirect:/users/view";
    }

    @GetMapping("/users/add")
    public String showAddForm() {
        return "users/add";
    }
    
    @PostMapping("/users/add")
    public String addStaff(@RequestParam Map<String, String> newStaff, Model model, HttpServletResponse response) {
        System.out.println("Adding Staff");
        
        if(!isValidStaffData(newStaff)) {
            model.addAttribute("invalidData", "Validation failed. Check name, email, and ensure scores are 1-10.");
            model.addAttribute("staff", newStaff);
            response.setStatus(400);
            return "users/add";
        }
       
        String newName = newStaff.get("name");
        String newEmail = newStaff.get("email");
        String newRoleType = newStaff.get("roleType");
        String newComment = newStaff.get("comment");
        int newClarity = Integer.parseInt(newStaff.get("clarity"));
        int newNiceness = Integer.parseInt(newStaff.get("niceness"));
        int newKnowledgeableScore = Integer.parseInt(newStaff.get("knowledgeableScore"));
        
        userRepo.save(new Staff(0, newName, newEmail, newRoleType, newClarity, newNiceness, newKnowledgeableScore, newComment));
        response.setStatus(201);
        return "redirect:/users/view";
    }

    @GetMapping("/users/modify/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model){
        Staff staff = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        model.addAttribute("staff", staff);
        return "users/modify";
    }

    @PostMapping("/users/modified/{id}")
    public String modifiedStaff(@PathVariable("id") int id, @RequestParam Map<String, String> modifiedStaff, Model model, HttpServletResponse response){
        System.out.println("Updating Staff with ID: " + id);
        
        if(!isValidStaffData(modifiedStaff)) {
            model.addAttribute("invalidData", "Validation failed. Check name, email, and ensure scores are 1-10.");
            model.addAttribute("staff", modifiedStaff);
            response.setStatus(400);
            return "users/modify";
        }
        
        String name = modifiedStaff.get("name");
        String email = modifiedStaff.get("email");
        String roleType = modifiedStaff.get("roleType");
        String comment = modifiedStaff.get("comment");
        int clarity = Integer.parseInt(modifiedStaff.get("clarity"));
        int niceness = Integer.parseInt(modifiedStaff.get("niceness"));
        int knowledgeableScore = Integer.parseInt(modifiedStaff.get("knowledgeableScore"));

        userRepo.save(new Staff(id, name, email, roleType, clarity, niceness, knowledgeableScore, comment));
        return "redirect:/users/view";
    }

    private boolean isValidStaffData(Map<String, String> staffData) {
        
        String name = staffData.get("name");
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$";
        String email = staffData.get("email");
        if (email == null || !email.matches(emailRegex)) {
            return false;
        }

        String comment = staffData.get("comment");
        if (comment != null && comment.length() > 500) {
            return false;
        }

        try {
            int clarity = Integer.parseInt(staffData.get("clarity"));
            int niceness = Integer.parseInt(staffData.get("niceness"));
            int knowledgeableScore = Integer.parseInt(staffData.get("knowledgeableScore"));
            
            if (clarity < 1 || clarity > 10 || niceness < 1 || niceness > 10 || knowledgeableScore < 1 || knowledgeableScore > 10) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
}