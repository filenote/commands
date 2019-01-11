package com.genrs.commands.content.container.impl;

import com.genrs.commands.content.container.ItemContainer;
import com.genrs.commands.content.container.StackingMode;

public class Bank extends ItemContainer {
    public Bank(Integer capacity) {
        super(capacity, StackingMode.STACK_ALL);
    }

    @Override
    public String toString() {
        return super.toString(this.getClass().getSimpleName());
    }
}
