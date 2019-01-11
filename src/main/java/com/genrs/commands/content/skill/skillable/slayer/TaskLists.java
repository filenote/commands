package com.genrs.commands.content.skill.skillable.slayer;

import java.util.ArrayList;
import java.util.Arrays;

public final class TaskLists {
    public static final ArrayList<PossibleTask> TURAEL_TASK_LIST = new ArrayList<>(Arrays.asList(
            new PossibleTask(MonsterType.BANSHEE, 15, 50, 8),
            new PossibleTask(MonsterType.BAT, 15, 50, 7)
    ));
}
