### 컬렉션 팩토리
1. 리스트 팩토리 : List.of() -> 변경 불가능한 리스트.
2. 집합 팩토리 : Set.of() -> 변경 불가능한 집합.
3. 맵 팩토리 : Map.of() -> 열 개 이하의 키와 값 쌍을 가진 작은 맵을 만들때는 Map.of() 가 유용<br>
              Map.ofEntries() -> 열 개 이상의 맵에서는 Map.Entry<K,V> 객체를 인수로 받는 이 팩토리 메서드를 이용하는 것이 좋음

리스트와 집합 처리
---------------
자바 8에서는 list, set 인터페이스에 아래 메서드를 추가.<br>
- removeIf : Predicate를 만족하는 요소 제거.
- replaceAll : 리스트에서 이용할 수 있는 기능으로 UnaryOperator 함수를 이용해 요소를 바꿈.
- sort : List 인터페이스에서 제공하는 기능으로 리스트를 정렬함.

개선된 ConcurrentHashMap
--------------------
ConcurrentHashMap 클래스는 동시성 친화적이며 최신 기술을 반영한 HashMap 버전<br>
내부 자료구조의 특정 부분만 잠궈 동시 추가, 갱신 작업을 허용.<br>

1. 리듀스와 검색
   - forEach : 각 (키,값) 쌍에 주어진 액션을 실행.
   - reduce : 모든 (키,값) 쌍을 제공된 리듀스 함수를 이용해 결과로 합침
   - search : 널이 아닌 값을 반환할 때까지 각 (키,값) 쌍에 함수를 적용
2. 계수 : 맵의 매핑 개수를 반환하는 mappingCount 메서드 제공
3. 집합뷰 : ConcurrentHashMap을 집합뷰로 반환하는 keySet이라는 새 메서드 제공.
