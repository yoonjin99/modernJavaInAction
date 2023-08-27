package com.example.modernJavaInAction.part2.chapter6;

import com.example.modernJavaInAction.part2.chapter4.Dish;
import com.example.modernJavaInAction.part2.chapter4.Type;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamCollector {
    /*
    * Collectors 에서 제공하는 메서드의 기능
    * 1. 스트림 요소를 하나의 값으로 리듀스하고 요약
    * 2. 요소 그룹화
    * 3. 요소 분할
    * */

    // 1. 리듀싱과 요약
    public void reduceAndSummarizationTest(List<Dish> menu){
        long beforeHowManyDishes = menu.stream().collect(Collectors.counting()); // counting 컬렉터는 다른 컬렉터와 함께 사용할 때 위력 발휘
        long afterHowManyDishes = menu.stream().count();

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator)); // 최댓값 검색
        // Optional<Dish> : 값을 포함하거나 포함하지 않을 수 있는 컨테이너 Optional 제공

        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories)); // 요약 팩토리 메서드 제공.

        String shortMenu =  menu.stream().map(Dish::getName).collect(Collectors.joining()); // 문자열 연결

        // reducing 을 이용해 가장 칼로리가 높은 요리를 찾는 방법
        // toList 컬렉터를 사용하는 대신 reduce 메서드를 사용할 수 있다.
        Optional<Dish> mostCalorieDishByReduce = menu.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
    }

    // 2. 그룹화
    public void groupingTest(List<Dish> menu){
        Map<Type, List<Dish>> dishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType)); // 분류 함수

        Map<Type, List<Dish>> caloricDishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));
        // 각 그룹의 요소와 필터링 된 요소를 재그룹화 함.
        // 비어있는 목록도 항목으로 추가됨.

        // 다수준 그룹화
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(Collectors.groupingBy(Dish::getType, // 첫번째 수준의 분류 함수
                        Collectors.groupingBy(dish -> { // 두번째 수준의 분류 함수
                            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));

    }

    // 3. 분할
    public void partitioningTest(List<Dish> menu){
        // 분할 함수라 불리는 프레디케이트를 분류 함수로 사용하는 특수한 그룹화 기능
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian)); // 분할 함수
        List<Dish> vegetarianDishes = partitionedMenu.get(true); // 모든 채식 요리 get

        // 분할의 장점
        // 분할 함수가 반환하는 참, 거짓 요소의 스트림 리스트를 모두 유지한다는 것이 분할의 장점.
        Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType =
                menu.stream()
                        .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType))); // 분할 함수

    }


}
