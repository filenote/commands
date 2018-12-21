package com.genrs.commands.model;

public enum Role {
    USER(0),
    MODERATOR(1),
    ADMINISTRATOR(2),
    DEVELOPER(3);

    private int value;
    public int getValue() {
        return this.value;
    }

    Role(int value) {
        this.value = value;
    }
}
