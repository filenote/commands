package com.genrs.commands.content.container.impl;

import com.genrs.commands.content.container.ItemContainer;
import com.genrs.commands.content.container.StackingMode;

public class Equipment extends ItemContainer {
    public Equipment() {
        super(10, StackingMode.STACK_STACKABLE_ITEMS);
    }
}
