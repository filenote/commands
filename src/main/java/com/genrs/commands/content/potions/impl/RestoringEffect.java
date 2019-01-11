package com.genrs.commands.content.potions.impl;

import com.genrs.commands.content.potions.BoostType;
import com.genrs.commands.content.potions.PotionEffect;
import com.genrs.commands.content.skill.Skill;
import com.genrs.commands.model.User;

import java.util.Arrays;

public class RestoringEffect extends PotionEffect {
    private static final float HOLY_WRENCH_BONUS = 0.02f;

    public RestoringEffect(BoostType type, Skill skill) {
        super(user -> restore(user, type, skill));
    }

    public RestoringEffect(BoostType type, Skill... skills) {
        super(user -> Arrays.stream(skills).forEach(skill -> restore(user, type, skill)));
    }

    public static void restore(User user, BoostType type, Skill skill) {
        int maxLevel = user.getSkillManager().getSkill(skill).getLevel();
        int currentLevel = user.getSkillManager().getSkill(skill).getLevel();
        if (maxLevel != currentLevel) {
            float bonusPercentage = 0f;
            if (skill == Skill.PRAYER /*&& user.hasWrench */) {
                bonusPercentage = HOLY_WRENCH_BONUS;
            }
            float percentage = type.getPercentage() + bonusPercentage;
            float restoreFromPercentage = percentage * maxLevel;
            int restore = (int) (restoreFromPercentage + type.getBase());
            int newLevel = restore + currentLevel;
            if (newLevel > maxLevel) newLevel = maxLevel;
            user.getSkillManager().getSkill(skill).setCurrentLevel(newLevel);
        }
    }
}
