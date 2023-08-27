package com.example.modernJavaInAction.part3.chapter9;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Validator {
    private final ValidatorStrategy strategy;

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
