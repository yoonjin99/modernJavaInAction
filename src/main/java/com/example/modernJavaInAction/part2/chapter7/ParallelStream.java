package com.example.modernJavaInAction.part2.chapter7;


import java.util.stream.Stream;

public class ParallelStream {
    /*
    * 병렬 스트림
    * 각각의 스레드에서 처리할 수 있도록 스트림 요소를 여러 청크로 분할한 스트림.
    * 모든 멀티코어 프로세서가 각각의 청크를 처리하도록 할당 가능.
    * */
    public long parallelSum(long n){
        return Stream.iterate(1L, i -> i+1)
                .limit(n)
                .parallel() // 스트림을 병렬 스트림으로 변환
                .reduce(0L, Long::sum);
    }

    /*
    * 병렬 스트림 효과적으로 사용하기
    * 1. 확신이 서지 않으면 직접 측정하라. 벤치마크로 직접 성능을 측정하는 것이 바람직
    * 2. 박싱을 주의. 자동 박싱과 언박싱은 성능을 크게 저하시킬 수 있는 요소. 되도록이면 기본형 특화 스트림을 사용하는 것이 좋음(IntStream, LongStream...)
    * 3. 순차 스트림보다 병렬 스트림에서 성능이 떨어지는 연산 존재. limit, findFirst 요소의 순서에 의존하는 연산.
    * 4. 스트림에서 수행하는 전체 파이프라인 연산 비용을 고려하기.
    * 5. 소량의 데이터에서는 병렬 스트림이 도움 되지 않음.
    * 6. 스트림을 구성하는 자료구조가 적절한지 확인. ArrayList 나 LinkedList
    * 7. 중간 연산이 스트림의 특성을 어떻게 바꾸는지에 따라 분해 과정의 성능이 달라질 수 있음
    * 8. 최종 연산의 병합 과정 비용을 살펴보기.
    * */
}
