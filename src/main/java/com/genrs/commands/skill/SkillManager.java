package com.genrs.commands.skill;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class SkillManager {

    private HashMap<SkillName, Skill> map;

    public SkillManager() {
        map = (HashMap<SkillName, Skill>) Arrays.stream(SkillName.values())
                .collect(Collectors.toMap(
                        skillName -> skillName,
                        skillName -> new Skill(UUID.randomUUID(), skillName, 1, 1, 0)));
        setLevel(SkillName.HITPOINTS, 10).setCurrentLevel(10).setExperience(1023);
    }

    public Skill setLevel(SkillName skill, Integer level) {
        return map.get(skill).setLevel(level);
    }

    public Skill setCurrentLevel(SkillName skill, Integer level) {
        return map.get(skill).setLevel(level);
    }

    public Skill setExperience(SkillName skill, Integer experience) {
        return map.get(skill).setExperience(experience);
    }

    public Skill getSkill(SkillName skill) {
        return map.get(skill);
    }

    public HashMap<SkillName, Skill> getSkillsMap() {
        return map;
    }

    @Override
    public String toString() {
        return "SkillManager{" +
                "map=" + map +
                '}';
    }
}
