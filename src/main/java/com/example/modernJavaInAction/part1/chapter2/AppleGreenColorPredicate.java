package com.example.modernJavaInAction.part1.chapter2;

/* 녹색 사과만 선택 */
public class AppleGreenColorPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}
