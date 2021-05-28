package ru.rustisk.calcalc.model;

import java.time.LocalDateTime;

public class MealWithExcess {
    protected int id;
    protected final LocalDateTime dateTime;
    protected final String description;
    protected final int calories;
    protected final boolean excess;

    public MealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "MealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean getExcess() {
        return excess;
    }

    public String getId() {
        return String.valueOf(this.id);
    }
}
