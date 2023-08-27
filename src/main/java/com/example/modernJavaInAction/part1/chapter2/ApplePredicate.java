package com.example.modernJavaInAction.part1.chapter2;

/* 선택 조건을 결정하는 인터페이스 정의
* 조건에 따라 filter 메서드가 다르게 동작 -> 전략 디자인 패턴
* 전략 디자인 패턴은 각 알고리즘을 캡슐화하는 알고리즘 패밀리를 정의해둔 다음에
* 런타임에 알고리즘을 선택하는 기법.
* ApplePredicate가 알고리즘 패밀리. AppleHeavyWeightPredicate 얘네가 전략.
* */
public interface ApplePredicate {
    boolean test(Apple apple);
}
