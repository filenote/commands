package com.genrs.commands.content.skill.skillable.slayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.genrs.commands.content.skill.skillable.slayer.TaskLists.TURAEL_TASK_LIST;

public enum SlayerMaster {
    TURAEL(TURAEL_TASK_LIST, 1, 3);

    private ArrayList<PossibleTask> list;
    private int level;
    private int combat;

    public ArrayList<PossibleTask> getList() {
        return list;
    }

    public int getLevel() {
        return level;
    }

    public int getCombat() {
        return combat;
    }

    SlayerMaster(ArrayList<PossibleTask> list, int level, int combat) {
        this.list = list;
        this.level = level;
        this.combat = combat;
    }

    @Override
    public String toString() {
        return "SlayerMaster{" +
                "list=" + list +
                ", level=" + level +
                ", combat=" + combat +
                '}';
    }

    public Optional<SlayerTask> getRandomSlayerTask(Integer level, HashSet<MonsterType> blockList) {
        ArrayList<PossibleTask> filteredList = this.list.stream()
                .filter(task -> !blockList.contains(task.getMonsterType()) && task.getMonsterType().level <= level)
                .collect(Collectors.toCollection(ArrayList::new));

        int totalWeight = filteredList.stream().mapToInt(PossibleTask::getWeight).sum();
        int randomNumber = ThreadLocalRandom.current().nextInt(0, totalWeight + 1);
        for (PossibleTask task : filteredList) {
            randomNumber -= task.getWeight();
            if (randomNumber <= 0) {
                int randomAmount = ThreadLocalRandom.current().nextInt(task.getLow(), task.getHigh());
                return Optional.of(new SlayerTask(task.getMonsterType(), randomAmount));
            }
        }
        return Optional.empty();
    }

}
