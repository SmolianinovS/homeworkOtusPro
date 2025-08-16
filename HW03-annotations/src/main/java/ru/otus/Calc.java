package ru.otus;

import ru.otus.anotations.After;
import ru.otus.anotations.Before;
import ru.otus.anotations.Test;

public class Calc {
    private int num1;
    private int num2;

    public Calc() {}

    public Calc(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Before
    public void init() {
        num1 = 10;
        num2 = 7;
    }

    @Test
    public void sum() {
        System.out.println("SumTestSuccessful");
    }

    @Test
    public void subs() {
        System.out.println("SumTestSuccessful");
    }

    @Test
    public void div(int num1, int num2) {
        System.out.println("SumTestNotSuccessful");
    }

    @After
    public void cleaning() {
        num1 = 0;
        num2 = 0;
    }
}
