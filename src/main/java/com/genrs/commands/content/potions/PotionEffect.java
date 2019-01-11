package com.genrs.commands.content.potions;

import com.genrs.commands.content.potions.impl.BoostingEffect;
import com.genrs.commands.content.potions.impl.RestoringEffect;
import com.genrs.commands.model.User;
import com.genrs.commands.content.skill.Skill;
import org.apache.commons.lang3.ArrayUtils;

import java.util.EnumMap;

public class PotionEffect {

    private static EnumMap<Potion, PotionEffect> map = initialize() ;

    private static EnumMap<Potion, PotionEffect> initialize() {
        EnumMap<Potion, PotionEffect> map = new EnumMap<>(Potion.class);
        map.put(Potion.ATTACK, new BoostingEffect(BoostType.NORMAL, Skill.ATTACK));
        map.put(Potion.SUPER_ATTACK, new BoostingEffect(BoostType.SUPER, Skill.ATTACK));
        map.put(Potion.STRENGTH, new BoostingEffect(BoostType.NORMAL, Skill.STRENGTH));
        map.put(Potion.SUPER_STRENGTH, new BoostingEffect(BoostType.SUPER, Skill.STRENGTH));
        map.put(Potion.DEFENCE, new BoostingEffect(BoostType.NORMAL, Skill.DEFENCE));
        map.put(Potion.SUPER_DEFENCE, new BoostingEffect(BoostType.SUPER, Skill.DEFENCE));
        map.put(Potion.COMBAT, new BoostingEffect(BoostType.LOW, Skill.STRENGTH, Skill.ATTACK));
        map.put(Potion.SUPER_COMBAT, new BoostingEffect(BoostType.SUPER, Skill.ATTACK,
                Skill.STRENGTH, Skill.DEFENCE));
        map.put(Potion.MAGIC, new BoostingEffect(BoostType.BASIC_NORMAL, Skill.MAGIC));
        map.put(Potion.PRAYER, new RestoringEffect(BoostType.PRAYER_RESTORE, Skill.PRAYER));
        map.put(Potion.SUPER_RESTORE, new RestoringEffect(BoostType.SUPER_RESTORE,
                ArrayUtils.removeElement(Skill.values(), Skill.HITPOINTS)));
        map.put(Potion.RESTORE, new RestoringEffect(BoostType.RESTORE,
                Skill.ATTACK, Skill.STRENGTH, Skill.DEFENCE, Skill.RANGED, Skill.MAGIC));
        return map;
    }

    private Effect effect;

    protected PotionEffect(Effect effect) {
        this.effect = effect;
    }

    static void use(User user, Potion potion) {
        map.get(potion).effect.execute(user);
    }
}
