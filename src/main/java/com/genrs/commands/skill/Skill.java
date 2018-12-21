package com.genrs.commands.skill;

import java.util.Objects;
import java.util.UUID;

public class Skill {

    private UUID id;
    private SkillName name;
    private Integer level;
    private Integer currentLevel;
    private Integer experience;

    public UUID getId() {
        return id;
    }

    public Skill setId(UUID id) {
        this.id = id;
        return this;
    }

    public SkillName getName() {
        return name;
    }

    public Skill setName(SkillName name) {
        this.name = name;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public Skill setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public Skill setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
        return this;
    }

    public Integer getExperience() {
        return experience;
    }

    public Skill setExperience(Integer experience) {
        this.experience = experience;
        return this;
    }

    public Skill(UUID id, SkillName name, Integer level, Integer currentLevel, Integer experience) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.currentLevel = currentLevel;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name=" + name +
                ", level=" + level +
                ", currentLevel=" + currentLevel +
                ", experience=" + experience +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return Objects.equals(getId(), skill.getId()) &&
                getName() == skill.getName() &&
                Objects.equals(getLevel(), skill.getLevel()) &&
                Objects.equals(getCurrentLevel(), skill.getCurrentLevel()) &&
                Objects.equals(getExperience(), skill.getExperience());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLevel(), getCurrentLevel(), getExperience());
    }
}
