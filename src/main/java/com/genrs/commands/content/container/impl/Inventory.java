package com.genrs.commands.content.container.impl;

import com.genrs.commands.content.container.ItemContainer;
import com.genrs.commands.content.container.StackingMode;

public class Inventory extends ItemContainer {
    public Inventory() {
        super(5, StackingMode.STACK_STACKABLE_ITEMS);
    }

    @Override
    public String toString() {
        return super.toString(this.getClass().getSimpleName());
    }
}
