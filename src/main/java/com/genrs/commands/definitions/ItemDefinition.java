package com.genrs.commands.definitions;

import java.util.HashMap;

public class ItemDefinition {

    private static HashMap<Integer, ItemDefinition> definitions = new HashMap<Integer, ItemDefinition>() {
        {
            put(995, new ItemDefinition(995, "Coins", true, 1));
            put(20, new ItemDefinition(20, "Item0", false, 200));
            put(1, new ItemDefinition(1, "E1", false, 10000, false));
            put(2, new ItemDefinition(2, "E2", false, 999));
            put(3, new ItemDefinition( 3, "E3", true, 20));
        }
    };

    private Integer id;
    private String name;
    private boolean stackable;
    private Integer value;
    private boolean tradeable;

    public boolean isTradeable() {
        return tradeable;
    }

    public void setTradeable(boolean tradeable) {
        this.tradeable = tradeable;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static ItemDefinition getDefinition(Integer id) {
        return definitions.get(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public ItemDefinition(Integer id, String name, boolean stackable, Integer value, boolean tradeable) {
        this.id = id;
        this.name = name;
        this.stackable = stackable;
        this.value = value;
        this.tradeable = tradeable;
    }
    public ItemDefinition(Integer id, String name, boolean stackable, Integer value) {
        this(id, name, stackable, value, true);
    }
}
