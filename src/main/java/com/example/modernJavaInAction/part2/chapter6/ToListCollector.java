package com.example.modernJavaInAction.part2.chapter6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


// 커스텀 컬렉터 구현하기
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new; //  수진 연산의 시발점
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add; // 탐색한 항목을 누적하고 바로 누적자를 고침.
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> { // 두번째 콘텐츠와 합쳐서 첫번째 누적자를 고침
            list1.add((T) list2); // 변경된 첫번째 누적자를 반환
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity(); // 항등 함수
    }

    @Override
    public Set<Characteristics> characteristics() {
        return (Set<Characteristics>) Collections.unmodifiableList(Arrays.asList(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
