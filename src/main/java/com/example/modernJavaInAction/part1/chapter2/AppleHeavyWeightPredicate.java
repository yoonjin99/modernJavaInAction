package com.example.modernJavaInAction.part1.chapter2;

/* 무거운 사과만 선택 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
