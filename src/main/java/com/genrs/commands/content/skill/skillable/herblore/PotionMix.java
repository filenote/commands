package com.genrs.commands.content.skill.skillable.herblore;

import com.genrs.commands.content.potions.Potion;
import com.genrs.commands.model.Item;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.genrs.commands.Main.VIAL_OF_WATER;
import static com.genrs.commands.content.skill.skillable.herblore.PotionItemRequirements.STRENGTH_ITEMS;
import static com.genrs.commands.content.skill.skillable.herblore.PotionItemRequirements.SUPER_COMBAT_ITEMS;

/**
 * Contains the necessary items required to create a potion.
 */
public enum PotionMix {
    STRENGTH(Potion.STRENGTH, VIAL_OF_WATER, Herb.TARROMIN, STRENGTH_ITEMS, 12, 50),
    SUPER_COMBAT(Potion.SUPER_COMBAT, -1, Herb.TORSTOL, SUPER_COMBAT_ITEMS, 90, 150);

    private Potion potion;
    private int base;
    private Herb herb;
    private Item[] secondary;
    private int level;
    private double experience;

    public static PotionMix getWithBase(Herb herb, int base) {
        List<PotionMix> possible = herbMap.get(herb);
        for (PotionMix mix : possible) {
            if (mix.base == base) return mix;
        }
        return null;
    }

    public static PotionMix getWithSecondary(Herb herb, int secondary) {
        List<PotionMix> possible = herbMap.get(herb);
        for (PotionMix mix : possible) {
            for (Item item : mix.getSecondary()) {
                if (item.getId() == secondary) return mix;
            }
        }
        return null;
    }

    public Potion getPotion() {
        return potion;
    }

    public int getBase() {
        return base;
    }

    public Herb getHerb() {
        return herb;
    }

    public Item[] getSecondary() {
        return secondary;
    }

    public int getLevel() {
        return level;
    }

    public double getExperience() {
        return experience;
    }

    PotionMix(Potion potion, int base, Herb herb, Item[] secondary, int level, double experience) {
        this.potion = potion;
        this.base = base;
        this.herb = herb;
        this.secondary = secondary;
        this.level = level;
        this.experience = experience;
    }

    public static final Map<Herb, List<PotionMix>> herbMap = Arrays.stream(PotionMix.values())
            .collect(Collectors.groupingBy(PotionMix::getHerb));

    /**
     * Contains set of all secondaries
     */
    private static final HashSet<Item> secondaries = Arrays.stream(PotionMix.values())
            .flatMap(mix -> Arrays.stream(mix.getSecondary()))
            .collect(Collectors.toCollection(HashSet::new));

    public PotionMix getFromSecondary(Herb herb, int id) {
        for (PotionMix mix : herbMap.get(herb)) {
            if (ArrayUtils.contains(mix.getSecondary(), id)) return mix;
        }
        return null;
    }

    public boolean isSecondary(Herb herb, int id) {
        for (PotionMix mix : herbMap.get(herb)) {
            if (ArrayUtils.contains(mix.getSecondary(), id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidSecondary(int id) {
        return secondaries.contains(new Item(id));
    }

    public static boolean isValidBase(int id) {
        return id == VIAL_OF_WATER;
    }

    @Override
    public String toString() {
        return "PotionMix{" +
                "potion=" + potion +
                ", base=" + base +
                ", herb=" + herb +
                ", secondary=" + Arrays.toString(secondary) +
                ", level=" + level +
                ", experience=" + experience +
                '}';
    }
}
