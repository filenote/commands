package com.genrs.commands.content.container;

import com.genrs.commands.model.Item;
import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Optional;

public abstract class ItemContainer {

    protected ItemContainer(Integer capacity, StackingMode stackingMode) {
        this.capacity = capacity;
        this.items = new Item[capacity];
        this.size = 0;
        this.stackingMode = stackingMode;
    }

    private Integer capacity;
    private Integer size;
    private Item[] items;
    private StackingMode stackingMode;

    public Optional<Item> transfer(Item item, ItemContainer receiver) {

        Integer id = item.getId();
        Integer amount = item.getAmount();
        Integer slot = getSlotForId(id);
        // System.out.println(id);
        if (slot == -1) {
            System.out.println("Item was not found in container.");
            return Optional.of(item);
        }

        Integer currentAmount = items[slot].getAmount();
        if (amount > currentAmount) {
            amount = currentAmount;
        }

        Optional<Item> returnedItem = receiver.add(new Item(id, amount));

        if (returnedItem.isPresent()) {
            Integer amountReturned = returnedItem.get().getAmount();
            // System.out.println(amountReturned);
            set(slot, new Item(id, currentAmount - amount + amountReturned));
            return returnedItem;
        }

        set (slot, new Item(id, currentAmount - amount));
        return Optional.empty();
    }

    public Optional<Item> add(Item item) {
        Integer amount = item.getAmount();
        Integer id = item.getId();

        boolean stackable = stackingMode == StackingMode.STACK_ALL ||
                (stackingMode == StackingMode.STACK_STACKABLE_ITEMS && item.getDefinition().isStackable());

        if (stackable) {
            return addStackableItem(item);
        }

        Integer remaining = amount;
        while (remaining > 0) {
            int slot = getNextEmptySlot();
            if (slot == -1) {
                return Optional.of(new Item(id, remaining));
            }
            set(slot, new Item(id, 1));
            remaining--;
        }

        return Optional.empty();
    }

    private Optional<Item> addStackableItem(Item item) {

        Integer amount = item.getAmount();
        Integer id = item.getId();
        Integer slot = getSlotForId(id);
        // System.out.println("slot=" +slot);

        if (slot == -1) {
            // no similar item was found, create a new slot
            slot = getNextEmptySlot();
            if (slot == -1) {
                // no empty slots
                return Optional.of(item);
            }
            // found empty slot
            set(slot, item);
            return Optional.empty();
        }

        //similar item was found
        long probableAmount = (long) amount + items[slot].getAmount();
        // System.out.println("probableAmount=" +probableAmount);
        int remainder = 0;
        if (probableAmount > Integer.MAX_VALUE) {
            remainder = (int) (probableAmount - Integer.MAX_VALUE);
            probableAmount -= remainder;
        }

        set(slot, new Item(id, (int) probableAmount));
        // System.out.println("remainder=" +remainder);

        return remainder == 0 ? Optional.empty() : Optional.of(new Item(id, remainder));
    }

    private Item set(Integer slot, Item item) {
        if (items[slot] != null) {
            reset(slot);
        }
        checkBounds(slot);

        Item current = items[slot];
        items[slot] = item;
        size++;
        return current;
    }

    private void checkBounds(Integer slot) {
        Preconditions.checkElementIndex(slot, capacity);
    }

    private Item reset(Integer slot) {
        checkBounds(slot);
        if (items[slot] == null) {
            return null;
        }
        Item current = items[slot];
        items[slot] = null;
        // System.out.println("Reset slot (" + slot + ") containing: " + current);
        size--;
        return current;
    }

    private Integer getNextEmptySlot() {
        for (int i = 0; i < capacity; i++) {
            if (items[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private Integer getSlotForId(Integer id) {
        for (int i = 0; i < capacity; i++) {
            if(items[i] != null && items[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public Item[] getItems() {
        return items;
    }

    public String toString(String container) {
        return container +"{" +
                "capacity=" + capacity +
                ", size=" + size +
                ", stackingMode=" + stackingMode +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
