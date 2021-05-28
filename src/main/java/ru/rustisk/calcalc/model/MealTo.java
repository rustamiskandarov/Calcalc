package ru.rustisk.calcalc.model;

import java.time.LocalDateTime;

public class MealTo {
    private final Integer id;
    private int calories;
    private boolean excess;
    private String description;
    private LocalDateTime dateTime;


    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.calories = calories;
        this.excess = excess;
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean getExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "calories=" + calories +
                ", excess=" + excess +
                '}';
    }

}
