package com.genrs.commands.content.skill.skillable.herblore;

import com.genrs.commands.model.Item;
import com.genrs.commands.model.User;

import java.util.Optional;

public class Herblore {

    public static boolean usable(Item item) {
        return usable(item.getId());
    }

    private static boolean usable(int id) {
        return getHerb(id) != null || PotionMix.isValidBase(id) || PotionMix.isValidSecondary(id);
    }

    private static Herb getHerb(Item item) {
        return getHerb(item.getId());
    }

    private static Herb getHerb(int id) {
        Optional<Herb> a = Optional.ofNullable(Herb.cleanMap.get(id));
        Optional<Herb> c = Optional.ofNullable(Herb.unfinishedMap.get(id));
        return a.orElse(c.orElse(null));
    }

    public static boolean mix(User user, int first, int second) {
        if (usable(first) && usable(second)) {
            if (PotionMix.isValidSecondary(first)) return finishMix(user, second, first);
            else if (PotionMix.isValidSecondary(second)) return finishMix(user, first, second);
            else if (PotionMix.isValidBase(first)) return startMix(user, second, first);
            else if (PotionMix.isValidBase(second)) return startMix(user, first, second);
        }
        return false;
    }

    public static boolean crush(User user, int pestle, int item) {
        if (CrushableItem.isCrushable(item) && pestle == 233) {
            user.getInventory().add(new Item(CrushableItem.get(item), 1));
            return true;
        }
        return false;
    }

    private static boolean startMix(User user, int herbId, int base) {
        // we have a base, but some potions have -1 as their base because they are secondary potions
        // those should never come through here though as secondaries are checked before this
        // get herb
        Herb herb = getHerb(herbId);
        if (herb != null) {
            // let's get the possible mix using the base and herb, since the base and herb can be used for mutiple
            // potion mixes we can probably get away with getting the first possible mix as we really don't know the
            // final form of the potion yet.
            PotionMix mix = PotionMix.getWithBase(herb, base);
            if (mix != null) {
                // if (base == vob && bunf != -1)
                // else
                System.out.println(mix.getHerb() + " (" + mix.getHerb().getUnfinished() + ") has been created.");
                return true;
            }
        }
        return false;
    }

    private static boolean finishMix(User user, int herbId, int secondary) {
        //first is an 'unfinished,' second is a secondary (can be anything).
        //lets get the herb
        Herb herb = getHerb(herbId);
        // make sure it's not null
        if (herb != null) {
            //let's try to get the possible mix that's being finished know one of the secondaries and the herb.
            PotionMix mix = PotionMix.getWithSecondary(herb, secondary);
            if (mix != null) {

                System.out.println(mix.getPotion().toString() + " has been created.");
                return true;
            }
        }
        return false;
    }
}
