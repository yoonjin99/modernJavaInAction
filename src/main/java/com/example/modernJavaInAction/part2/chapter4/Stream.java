package com.example.modernJavaInAction.part2.chapter4;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {
    /*
    * 스트림은 자바 8 api에 새로 추가된 기능
    * 스트림을 이용하면 선언형으로 컬렉션 데이터 처리 가능.
    * */
    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
    );

    public void beforeJava8(List<Dish> menu){
        List<Dish> lowCaloricDishes = new ArrayList<>(); // 컨테이너 역할만 하는 중간 변수.
        for(Dish dish : menu){
            if(dish.getCalories() < 400){
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() { // 익명 클래스로 요리 정렬
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish dish : lowCaloricDishes){
            lowCaloricDishesName.add(dish.getName()); // 정렬된 리스트를 처리하면서 요리 이름 선택
        }
    }

    /* 스트림 API는 매우 비싼 연산
     * 특징
     * 1. 선언형 : 더 간결하고 가독성이 좋아짐
     * 2. 조립가능 : 유연성이 좋아짐
     * 3. 병렬화 : 성능이 좋아짐.
     *  */
    public void afterJava8(List<Dish> menu) {
        List<String> lowCaloricDishesName = menu.stream()
                .filter(d -> d.getCalories() < 400) // 400칼로리 이하의 요리 선택
                .sorted(Comparator.comparing(Dish::getCalories)) // 칼로리로 요리 정렬
                .map(Dish::getName) // 요리명 추출
                .toList(); // 모든 요리명을 리스트에 저장

        List<String> lowCaloricDishesNameByParallel = menu.parallelStream() // 멀티코어 아키텍처에서 병렬로 실행 가능.
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .toList();

        List<String> lowCaloricDishesName2 = menu.stream() // 요리 리스트에서 스트림 얻기
                .filter(d -> d.getCalories() < 400) // 중간연산
                .map(Dish::getName) // 중간연산
                .limit(3) // 중간연산
                .toList(); // 스트림을 리스트로 변환 최종연산
    }

    /* 스트림 이용 과정
    * 질의를 수행할 (컬렉션 같은) 데이터 소스
    * 스트림 파이프라인을 구성할 중간 연산 연결
    * 스트림 파이프라인을 실행하고 결과를 만들 최종 연산
    * 빌더 패턴과 비슷. -> 호출을 연결해서 설정을 만듦. 준비된 설정에 build 메서드를 호출
    *  */

}
