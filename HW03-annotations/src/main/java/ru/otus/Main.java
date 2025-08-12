package ru.otus;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting test of calculator");
        TestFrame testFrame = new TestFrame();
        testFrame.start(Calc.class);
    }
}
