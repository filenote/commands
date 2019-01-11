package com.genrs.commands.content.potions;

import com.genrs.commands.model.Item;
import com.genrs.commands.model.User;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Optional;

public enum  Potion {
    STRENGTH(119, 117, 115, 113),
    SUPER_STRENGTH(161,159, 157, 2440),
    ATTACK(125, 123, 121, 2428),
    SUPER_ATTACK(149, 147, 145, 2436),
    DEFENCE(137, 135, 133, 2432),
    SUPER_DEFENCE(167, 165, 163, 2442),
    RANGING(173, 171, 169, 2444),
    SUPER_RANGING(11725, 11724, 11723, 11722),
    FISHING(155, 153, 151, 2438),
    PRAYER(143, 141, 139, 2434),
    ANTIFIRE(2458, 2456, 2454, 2452),
    ZAMORAK_BREW(193, 191, 189, 2450),
    ANTIPOISON(179, 177, 175, 2446),
    RESTORE(131, 129, 127, 2430),
    MAGIC(3046, 3044, 3042, 3040),
    SUPER_MAGIC(11729, 11728, 11727, 11726),
    SUPER_RESTORE(3030, 3028, 3026, 3024),
    ENERGY(3014, 3012, 3010, 3008),
    SUPER_ENERGY(3022, 3020, 3018, 3016),
    AGILITY(3038, 3036, 3034, 3032),
    SARADOMIN_BREW(6691, 6689, 6687, 6685),
    ANTIPOISON1(5949, 5947, 5945, 5943),
    ANTIPOISON2(5958, 5956, 5954, 5952),
    SUPER_ANTIPOISON(185, 183, 181, 2448),
    RELICYMS_BALM(4848, 4846, 4844, 4842),
    SERUM_207(3414, 3412, 3410, 3408),
    COMBAT(9745, 9743, 9741, 9739),
    SUPER_COMBAT(12701, 12699, 12697, 12695);

    private int[] id;

    Potion(int... id) {
        this.id = id;
    }

    public int[] getId() {
        return id;
    }

    public static boolean drink(User user, int item, int slot) {
        Optional<Potion> potion = forId(item);
        if (!potion.isPresent()) return false;

        PotionEffect.use(user, potion.get());
        int newDose = potion.get().getDose(item) - 1;
        int indexOfDose = newDose - 1;
        if (inBounds(indexOfDose, potion.get())) {
            System.out.println(potion.get().id[indexOfDose]);
        }
        return false;
    }

    private static boolean inBounds(int index, Potion potion) {
        return index >= 0 && index < potion.id.length;
    }

    private static HashMap<Integer, Potion> potions = new HashMap<>();
    static {
        for (Potion potion : Potion.values()) {
            potions.put(potion.id[0], potion);
            potions.put(potion.id[1], potion);
            potions.put(potion.id[2], potion);
            potions.put(potion.id[3], potion);
        }
    }

    public int getDose(int id) {
        Optional<Potion> potion = forId(id);
        if (potion.isPresent() && potion.get().equals(this)) {
            return ArrayUtils.indexOf(this.id, id);
        }
        return -1;
    }

    public static Optional<Potion> forId(int item) {
        return Optional.ofNullable(potions.get(item));
    }

    public static boolean combine(User user, Item used, Item target) {
        Optional<Potion> first = forId(used.getId());
        Optional<Potion> second = forId(target.getId());

        if (first.isPresent() && second.isPresent() && first.get().equals(second.get())) {
            int firstDose = ArrayUtils.indexOf(first.get().getId(), used.getId()) + 1;
            int secondDose = ArrayUtils.indexOf(first.get().getId(), target.getId()) + 1;

            int combinedAmount = firstDose + secondDose;
            int firstVial = combinedAmount - 4 - 1;
            int secondVial = combinedAmount - firstVial - 2;

            if (firstVial >= 0) System.out.println(first.get().getId()[firstVial]);
            else System.out.println("empty");
            System.out.println(first.get().getId()[secondVial]);
            return true;
        }
        return false;
    }

}
