package com.example.modernJavaInAction.part2.chapter5;

import com.example.modernJavaInAction.part2.chapter4.Dish;

import java.util.List;

public class StreamUses {
    /*
    * filtering
    * */
    public void filteringTest(List<Dish> menu){
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian) // 채식 요리인지 확인하는 메서드 참조
                .toList();

        List<Dish> filteredMenu = menu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .toList();

        /* 자바9 스트림의 요소를 효과적으로 선택할 수 있도록 takeWile, dropWhile 메서드 지원 */
        List<Dish> slicedMenu1 = menu.stream()
                .takeWhile(dish -> dish.getCalories() < 320) // predicate가 처음으로 거짓이 되는 지점까지 발견된 요소만 return
                .toList();

        // 무한한 남은 요소를 가진 무한 스트림에서도 동작.
        List<Dish> slicedMenu2 = menu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)  // predicate가 처음으로 거짓이 되는 지점까지 발견된 요소를 버리고 바로 return
                .toList();
    }

    /*
    * 쇼트서킷 평가
    * 표현식에서 하나라도 거짓이라는 결과가 나오면 나머지 표현식의 결과와 상관없이 전체 결과도 거짓이 됨.
    * 이러한 상황을 쇼트 서킷이라 부름.
    * allMatch, noneMatch, findFirst, findAny, limit 등
    * 원하는 요소를 찾으면 즉시 결과를 반환.
    * */
}
