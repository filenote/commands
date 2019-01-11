package com.genrs.commands.content.skill.skillable.slayer;

public class PossibleTask {

    private MonsterType monsterType;
    private int low;
    private int high;
    private int weight;

    public PossibleTask(MonsterType monster, int low, int high, int weight) {
        this.monsterType = monster;
        this.low = low;
        this.high = high;
        this.weight = weight;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public PossibleTask setCharacterId(MonsterType monsterType) {
        this.monsterType = monsterType;
        return this;
    }

    public int getLow() {
        return low;
    }

    public PossibleTask setLow(int low) {
        this.low = low;
        return this;
    }

    public int getHigh() {
        return high;
    }

    public PossibleTask setHigh(int high) {
        this.high = high;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public PossibleTask setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public String toString() {
        return "PossibleTask{" +
                "monsterType=" + monsterType +
                ", low=" + low +
                ", high=" + high +
                ", weight=" + weight +
                '}';
    }
}
