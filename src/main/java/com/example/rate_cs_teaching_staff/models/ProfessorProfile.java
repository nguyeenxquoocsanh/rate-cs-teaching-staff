package com.example.rate_cs_teaching_staff.models;

public class ProfessorProfile implements StaffMemberProfile{
    @Override
    public String displayTitle(String name){
        return "Professor " + name;
        
    }
    
}
