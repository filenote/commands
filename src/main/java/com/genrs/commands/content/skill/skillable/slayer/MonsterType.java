package com.genrs.commands.content.skill.skillable.slayer;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public enum MonsterType {
    BANSHEE(1, "banshees", 414, 7272, 7390),
    BAT(1, "bats", 2827, 2834, 3201, 4504, 4562, 5791, 6824, 7061);

    HashSet<Integer> ids;
    String name;
    Integer level;

    private static final Map<MonsterType, Integer> levelRequirements = Arrays.stream(MonsterType.values())
            .filter(monster -> monster.level != 1)
            .collect(toMap(monster -> monster, monster -> monster.level));

    public static boolean hasSlayerRequirement(MonsterType monster) {
        return levelRequirements.containsKey(monster);
    }

    public static Optional<Integer> getSlayerRequirement(MonsterType monster) {
        return Optional.ofNullable(levelRequirements.get(monster));
    }

    MonsterType(Integer level, String name, Integer... ids) {
        this.level = level;
        this.name = name;
        this.ids = new HashSet<>(Arrays.asList(ids));
    }
}
