package com.genrs.commands.model;

import com.genrs.commands.definitions.ItemDefinition;

import java.util.Objects;

public class Item {

    private Integer id;
    private Integer amount;

    public Item(Item item) {
        this(item.getId(), item.getAmount());
    }

    public ItemDefinition getDefinition() {
        return ItemDefinition.getDefinition(this.id);
    }
    public Integer getValue() {
        return this.getDefinition().getValue();
    }

    public boolean isTradeable() {
        return this.getDefinition().isTradeable();
    }

    public boolean isStackable() {
        return this.getDefinition().isStackable();
    }

    public Integer getId() {
        return id;
    }

    public Item setId(Integer id) {
        this.id = id;
        return this;
    }

    public Item add(Integer amount) {
        long probableAmount = (long) amount + (long) this.amount;
        if (probableAmount >= Integer.MAX_VALUE) {
            this.amount = Integer.MAX_VALUE;
        } else if (probableAmount < 0) {
            this.amount = 0;
        } else {
            this.amount = (int) probableAmount;
        }
        return this;
    }

    public boolean isValid() {
        return id > 0 && amount >= 1;
    }

    public Item increment() {
        return this.add(1);
    }

    public Item decrement() {
        return this.subtract(1);
    }

    public Item subtract(Integer amount) {
        return this.add(-amount);
    }

    public Integer getAmount() {
        return amount;
    }

    public Item setAmount(Integer amount) {
        if (amount < 0) this.amount = 0;
        else this.amount = amount;
        return this;
    }

    public Item() {
        this(-1, 0);
    }

    public Item(Integer id, Integer amount) {
        this.setAmount(amount);
        this.id = id;
    }

    public Item(Integer id) {
        this(id, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getId(), item.getId()) &&
                Objects.equals(getAmount(), item.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
