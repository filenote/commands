package com.genrs.commands.model;

import com.genrs.commands.skill.SkillManager;

public class User {

    private String username;
    private Role role;
    private SkillManager skillManager;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public void setSkillManager(SkillManager skillManager) {
        this.skillManager = skillManager;
    }

    public User(String username, Role role, SkillManager skillManager) {
        this.username = username;
        this.role = role;
        this.skillManager = skillManager;
    }

    public User(String username, Role role) {
        this.username = username;
        this.role = role;
        this.skillManager = new SkillManager();
    }

    public User(String username) {
        this.username = username;
        this.role = Role.USER;
        this.skillManager = new SkillManager();
    }

    public boolean authorized(Role required) {
        return role.getValue() >= required.getValue();
    }
}
