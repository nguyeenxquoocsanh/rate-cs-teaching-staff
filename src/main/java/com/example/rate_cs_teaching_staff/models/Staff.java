package com.example.rate_cs_teaching_staff.models;

public class Staff {
    private String id;
    private String name;
    private String email;
    private String roleType;
    private int clarity;
    private int niceness;
    private int knowledgeableScore;
    private String comment;
    //there are recommended attributes called createdAt and updatedAt, maybe can add later?

    // Constructors, getters, and setter
    public Staff() {
    }

    public Staff(String id, String name, String email, String roleType, int clarity, int niceness,
            int knowledgeableScore, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
        this.clarity = clarity;
        this.niceness = niceness;
        this.knowledgeableScore = knowledgeableScore;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public int getClarity() {
        return clarity;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public int getNiceness() {
        return niceness;
    }

    public void setNiceness(int niceness) {
        this.niceness = niceness;
    }

    public int getKnowledgeableScore() {
        return knowledgeableScore;
    }

    public void setKnowledgeableScore(int knowledgeableScore) {
        this.knowledgeableScore = knowledgeableScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
