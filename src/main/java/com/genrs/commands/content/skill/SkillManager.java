package com.genrs.commands.content.skill;

import com.genrs.commands.content.skill.skillable.SkillDetails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class SkillManager {

    private HashMap<Skill, SkillDetails> map;

    public SkillManager() {
        map = (HashMap<Skill, SkillDetails>) Arrays.stream(Skill.values())
                .collect(Collectors.toMap(
                        skillName -> skillName,
                        skillName -> new SkillDetails(UUID.randomUUID(), skillName, 1, 1, 0)));
        setLevel(Skill.HITPOINTS, 10).setCurrentLevel(10).setExperience(1023);
    }

    public SkillDetails setLevel(Skill skill, Integer level) {
        return map.get(skill).setLevel(level).setCurrentLevel(level).setExperience(getExperienceForLevel(level));
    }

    public SkillDetails setCurrentLevel(Skill skill, Integer level) {
        return map.get(skill).setLevel(level);
    }

    public SkillDetails setExperience(Skill skill, Integer experience) {
        return map.get(skill).setExperience(experience);
    }

    public SkillDetails getSkill(Skill skill) {
        return map.get(skill);
    }

    public HashMap<Skill, SkillDetails> getSkillsMap() {
        return map;
    }

    public static Integer getExperienceForLevel(Integer level) {
        if (level <= 1) return 0;
        int a = level - 1;
        double b = 300;
        double c = Math.pow(2, a/7.0);
        int val = (int) Math.floor(( a + (b * c))/4);
        return getExperienceForLevel(level - 1) + val;
    }

    @Override
    public String toString() {
        return "SkillManager{" +
                "map=" + map +
                '}';
    }

    public boolean check(Skill skill, int level) {
        return level >= getSkill(skill).getCurrentLevel();
    }
}
