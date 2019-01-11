package com.genrs.commands.content.potions;

public enum BoostType {
    BASIC_NORMAL(4, 0f),
    LOW(1, 0.08f),
    NORMAL(3, 0.10f),
    SUPER(5, 0.15f),
    PRAYER_RESTORE(7, 0.25f),
    RESTORE(10, 0.30f),
    SUPER_RESTORE(8, 0.25f);

    /**
     * The base amount for boost type.
     */
    private final int base;

    /**
     * The percentage that will be taken of Skill
     */
    private final float percentage;

    /**
     * Creates a new {@link BoostType}.
     *
     * @param base the amount this type will boost by.
     * @param percentage the percentage of skill that will be added
     */
    BoostType(int base, float percentage) {
        this.base = base;
        this.percentage = percentage;
    }

    /**
     * Gets the base this type will boost by.
     *
     * @return the boost amount.
     */
    public final int getBase() {
        return this.base;
    }

    /**
     * Gets the percentage of this type.
     */
    public final float getPercentage() {
        return this.percentage;
    }
}
