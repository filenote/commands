package com.genrs.commands.content.skill.skillable.slayer;

public class SlayerTask {
    private MonsterType monsterType;
    private int amount;

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public SlayerTask(MonsterType monsterType, int amount) {
        this.monsterType = monsterType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SlayerTask{" +
                "monsterType=" + monsterType +
                ", amount=" + amount +
                '}';
    }
}
