package com.genrs.commands.content.potions.impl;

import com.genrs.commands.content.potions.BoostType;
import com.genrs.commands.content.potions.PotionEffect;
import com.genrs.commands.content.skill.Skill;
import com.genrs.commands.model.User;

import java.util.Arrays;

public class BoostingEffect extends PotionEffect {
    public BoostingEffect(BoostType type, Skill skill) {
        super(user -> boost(user, type, skill));
    }

    public BoostingEffect(BoostType type, Skill... skill) {
        super(user -> Arrays.stream(skill).forEach(skillName -> boost(user, type, skillName)));
    }

    public static void boost(User user, BoostType type, Skill skill) {
        int maxLevel = user.getSkillManager().getSkill(skill).getLevel();
        int percentage = (int) Math.floor(maxLevel * type.getPercentage());
        int boost = percentage + type.getBase();
        user.getSkillManager().getSkill(skill).addToCurrentLevel(boost);
    }
}