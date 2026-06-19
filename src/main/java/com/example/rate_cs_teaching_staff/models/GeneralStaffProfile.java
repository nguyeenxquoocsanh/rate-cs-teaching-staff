package com.example.rate_cs_teaching_staff.models;

public class GeneralStaffProfile implements StaffMemberProfile  {
    @Override
    public String displayTitle(String name){
        return "Staff " + name;
    }
    
}
