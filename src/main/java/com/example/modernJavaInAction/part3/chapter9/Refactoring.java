package com.example.modernJavaInAction.part3.chapter9;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Refactoring {
    /*
    * 코드 가독성 개선
    * 어떤 코드를 다른 사람도 쉽게 이해할 수 있음을 의미.
    * 1. 익명 클래스를 람다 표현식으로 리팩토링 하기
    * 2. 람다 표현식을 메서드 참조로 리팩토링 하기
    * 3. 명령형 데이터 처리를 스트림으로 리팩토링 하기
    * */

    /*
    * 람다 표현식으로 기존의 많은 객체지향 디자인 패턴을 제거하거나 간결하게 재구현 가능
    * */

    /*전략
    * 런타임에 적절한 알고리즘을 선택하는 기법
    * */
    public void strategyTest(){
        Validator numericValidator = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b1 = numericValidator.validate("aaaaaa"); // 람다를 전달.
        // 새로운 클래스를 구현할 필요 없이 람다 표현식을 직접 전달하면 코드가 간결해짐.
    }
    /*템플릿메서드
    * 알고리즘 개요를 제시한 다음에 알고리즘의 일부를 고칠 수 있는 유연함을 제공해야 할 때 사용
    * */
    public void templateMethodTest(){
        new OnlineBankingLambda().processCustomer(1336, (Customer c) -> System.out.println("Hello" + c.getName()));
    }
    /*옵저버
    * 어떤 이벤트가 발생했을 대 객체가 다른 객체 리스트에 자동으로 알림을 보내야 하는 상황에서 사용.
    * */
    public void observerTest(){
        Feed f = new Feed();
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY!!" + tweet);
                // 이런식으로 명시적으로 인스턴스화 하지 않고 람다 표현식을 직접 전달해서 실행할 동작을 지정 할 수 있음.
            }
        });
    }
}

// template method pattern test code
class OnlineBankingLambda{
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = DataBase.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }
}

@Getter
class Customer{
    private String name;
}

class DataBase{
    public static Customer getCustomerWithId(int id){
        return null;
    }
}

interface Observer {
    void notify(String tweet);
}

class NYTimes implements Observer {

    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY!!" + tweet);
        }
    }
}

class Guardian implements Observer{

    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("queen")){
            System.out.println("Yet more news from London ..." + tweet);
        }
    }
}

class LeMode implements Observer{

    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news!" + tweet);
        }
    }
}

interface Subject{
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}

class Feed implements Subject{
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(Observer::notify);
    }
}
