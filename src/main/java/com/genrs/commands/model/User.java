package com.genrs.commands.model;

import com.genrs.commands.content.container.impl.Bank;
import com.genrs.commands.content.container.impl.Equipment;
import com.genrs.commands.content.container.impl.Inventory;
import com.genrs.commands.content.skill.SkillManager;
import com.genrs.commands.content.skill.skillable.slayer.MonsterType;

import java.util.HashSet;

public class User {

    private static final Integer BANK_CAPACITY = 10;
    private String username;
    private Role role;
    private SkillManager skillManager;
    private Bank bank;
    private Inventory inventory;
    private Equipment equipment;
    private HashSet<MonsterType> blockList;

    public HashSet<MonsterType> getBlockList() {
        return blockList;
    }

    public User setBlockList(HashSet<MonsterType> blockList) {
        this.blockList = blockList;
        return this;
    }

    public HashSet<MonsterType> addToBlockList(MonsterType monsterType) {
        blockList.add(monsterType);
        return blockList;
    }

    public HashSet<MonsterType> removeFromBlockList(MonsterType monsterType) {
        blockList.remove(monsterType);
        return blockList;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Bank getBank() {
        return bank;
    }

    public User setBank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public User setInventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public void setSkillManager(SkillManager skillManager) {
        this.skillManager = skillManager;
    }

    public User(String username, Role role, SkillManager skillManager, Bank bank, Inventory inventory, Equipment equipment,
                HashSet<MonsterType> blockList) {
        this.username = username;
        this.role = role;
        this.skillManager = skillManager;
        this.bank = bank;
        this.inventory = inventory;
        this.equipment = equipment;
        this.blockList = blockList;
    }

    public User(String username, Role role) {
        this(username, role, new SkillManager(), new Bank(BANK_CAPACITY), new Inventory(), new Equipment(), new HashSet<>());
    }

    public User(String username) {
        this(username, Role.USER);
    }

    public boolean authorized(Role required) {
        return role.getValue() >= required.getValue();
    }
}
