package com.genrs.commands.content.skill.skillable;

import com.genrs.commands.content.skill.Skill;

import java.util.Objects;
import java.util.UUID;

public class SkillDetails {

    private UUID id;
    private Skill name;
    private Integer level;
    private Integer currentLevel;
    private Integer experience;

    public UUID getId() {
        return id;
    }

    public SkillDetails setId(UUID id) {
        this.id = id;
        return this;
    }

    public Skill getName() {
        return name;
    }

    public SkillDetails setName(Skill name) {
        this.name = name;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public SkillDetails setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public SkillDetails setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
        return this;
    }

    public SkillDetails addToCurrentLevel(Integer amount) {
        this.currentLevel = this.currentLevel + amount;
        return this;
    }

    public Integer getExperience() {
        return experience;
    }

    public SkillDetails setExperience(Integer experience) {
        this.experience = experience;
        return this;
    }

    public SkillDetails(UUID id, Skill name, Integer level, Integer currentLevel, Integer experience) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.currentLevel = currentLevel;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "SkillDetails{" +
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
        if (!(o instanceof SkillDetails)) return false;
        SkillDetails skillDetails = (SkillDetails) o;
        return Objects.equals(getId(), skillDetails.getId()) &&
                getName() == skillDetails.getName() &&
                Objects.equals(getLevel(), skillDetails.getLevel()) &&
                Objects.equals(getCurrentLevel(), skillDetails.getCurrentLevel()) &&
                Objects.equals(getExperience(), skillDetails.getExperience());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLevel(), getCurrentLevel(), getExperience());
    }
}
