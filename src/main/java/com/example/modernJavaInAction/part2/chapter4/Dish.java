package com.example.modernJavaInAction.part2.chapter4;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.vegetarian = vegetarian;
        this.type = type;
    }
}
