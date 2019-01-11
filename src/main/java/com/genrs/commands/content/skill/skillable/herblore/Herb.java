package com.genrs.commands.content.skill.skillable.herblore;

import com.genrs.commands.content.skill.Skill;
import com.genrs.commands.model.User;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Herb {
    GUAM(199, 249, 91, -1, 1),
    TARROMIN(253, 203, 95, -1, 5),
    CADANTINE(265, 215, 107, 22443, 10),
    TORSTOL(269, 219, 111, -1, 75);

    /**
     * Id to clean version of herb
     */
    private int clean;

    /**
     * Id to grimy version of herb
     */
    private int grimy;

    /**
     * Id for unfinished herb potion
     */
    private int unfinished;

    /**
     * Id for blood version of herb potion
     */
    private int bloodUnfinished;

    /**
     * Level required to clean herb
     */
    private int level;

    public int getClean() {
        return clean;
    }

    public int getGrimy() {
        return grimy;
    }

    public int getUnfinished() {
        return unfinished;
    }

    public int getBloodUnfinished() {
        return bloodUnfinished;
    }

    public int getLevel() {
        return level;
    }

    Herb(int clean, int grimy, int unfinished, int bloodUnfinished, int level) {
        this.clean = clean;
        this.grimy = grimy;
        this.unfinished = unfinished;
        this.bloodUnfinished = bloodUnfinished;
        this.level = level;
    }

    /**
     * Maps grimy herbs (id) to Herb enum
     */
    public static final Map<Integer, Herb> grimyMap = Arrays.stream(Herb.values())
            .collect(Collectors.toMap(Herb::getGrimy, herb -> herb));

    public static final Map<Integer, Herb> cleanMap = Arrays.stream(Herb.values())
            .collect(Collectors.toMap(Herb::getClean, herb -> herb));

    public static final Map<Integer, Herb> unfinishedMap = Arrays.stream(Herb.values())
            .collect(Collectors.toMap(Herb::getUnfinished, herb -> herb));

    public static boolean clean(User user, int grimy, int slot) {
        Herb herb = grimyMap.get(grimy);
        if (herb != null) {
            if (user.getInventory().getItems()[slot].getId() == grimy) {
                boolean finish = user.getSkillManager().check(Skill.HERBLORE, herb.level);
                if (finish) {
                    // remove grimy
                    // add clean
                }
            }
        }
        return false;
    }
}
