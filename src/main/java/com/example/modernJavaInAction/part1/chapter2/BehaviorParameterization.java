package com.example.modernJavaInAction.part1.chapter2;

import java.util.ArrayList;
import java.util.List;

public class BehaviorParameterization {
    /*
    * 동작 파라미터화
    * 변화하는 요구사항에 좀 더 유연하게 대응할 수 있는 방법.
    * */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){ // 프레디케이트 객체로 사과 검사 조건을 캡슐화 함. 가장 중요한 구현.
                // filterApples 메서드의 새로운 동작을 정의하는 것이 test 메서드.
                result.add(apple);
            }
        }
        return result;
    }
    // 즉 우리는 filterApples 메서드의 동작을 파라미터화한 것이다.
    // 컬렉션 탐색 로직과 각 항목에 적용할 동작을 분리할 수 있다는 것이 동작 파라미터화의 강점.

    /*
     * 자바는 클래스의 선언과 인스턴스화를 동시에 수행할 수 있도록 익명 클래스 라는 기법을 제공한다.
     * 코드가 깔끔하진 않다는 단점이 있음.
     * */
    public static List<Apple> anonymousClassTest(List<Apple> inventory){
        List<Apple> redAppleds = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            } // filterApples 메서드의 동작을 직접 파라미터화 했다!
        });

        return redAppleds;
    }

    /* 람다 표현식 사용
    * 유연성과 간결함 둘 다 가지고 있는 자바8의 핵심 기능
    * */
    public static List<Apple> lambdaTest(List<Apple> inventory){
        List<Apple> redAppleds = filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));
        return redAppleds;
    }
}


