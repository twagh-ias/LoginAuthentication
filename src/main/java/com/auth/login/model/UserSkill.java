package com.auth.login.model;

public class UserSkill {
    private String e_id;
    private String p_skills;
    private String p_proficiency_level;
    private String a_skills;
    private String a_proficiency_level;
    private String aspired_skills;
    private String a_self_rating;
    private String p_self_rating;

    public String getAspired_skills() {
        return aspired_skills;
    }

    public void setAspired_skills(String aspired_skills) {
        this.aspired_skills = aspired_skills;
    }

    public String getA_self_rating() {
        return a_self_rating;
    }

    public void setA_self_rating(String a_self_rating) {
        this.a_self_rating = a_self_rating;
    }

    public String getP_self_rating() {
        return p_self_rating;
    }

    public void setP_self_rating(String p_self_rating) {
        this.p_self_rating = p_self_rating;
    }

    public String getP_skills() {
        return p_skills;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public void setP_skills(String p_skills) {
        this.p_skills = p_skills;
    }

    public String getP_proficiency_level() {
        return p_proficiency_level;
    }

    public void setP_proficiency_level(String p_proficiency_level) {
        this.p_proficiency_level = p_proficiency_level;
    }

    public String getA_skills() {
        return a_skills;
    }

    public void setA_skills(String a_skills) {
        this.a_skills = a_skills;
    }

    public String getA_proficiency_level() {
        return a_proficiency_level;
    }

    public void setA_proficiency_level(String a_proficiency_level) {
        this.a_proficiency_level = a_proficiency_level;
    }
}
