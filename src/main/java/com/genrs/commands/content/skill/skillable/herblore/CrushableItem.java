package com.genrs.commands.content.skill.skillable.herblore;

import com.genrs.commands.model.Item;

import java.util.HashMap;

public class CrushableItem {

    private static final HashMap<Integer, Integer> crushables = new HashMap<>();
    static {

    }

    public static boolean isCrushable(int id) {
        return crushables.containsKey(id);
    }

    public static int get(int id) {
        return crushables.get(id);
    }
}
