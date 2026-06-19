package com.example.rate_cs_teaching_staff.models;

public class TAProfile implements StaffMemberProfile {
    @Override
    public String displayTitle(String name){
        return "TA " + name;
    }
    
}
