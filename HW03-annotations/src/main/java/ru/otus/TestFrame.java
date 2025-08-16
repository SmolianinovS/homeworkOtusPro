package ru.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.otus.anotations.After;
import ru.otus.anotations.Before;
import ru.otus.anotations.Statistic;
import ru.otus.anotations.Test;

public class TestFrame {
    public void start(Class<?> clazz) {
        Statistic results = executeTests(clazz);
        System.out.printf("\nSuccessful: %d\nFailed: %d\n", results.getSuccessful(), results.getFailed());
    }

    private Statistic executeTests(Class<?> clazz) {
        Map<String, List<Method>> methods = analyseMethodsOfClass(clazz);
        int successful = 0;
        int failed = 0;

        for (Method test : methods.getOrDefault("Test", List.of())) {
            Object instance = createInstance(clazz);
            try {
                System.out.println("\nRunning test: " + test.getName());
                runMethods(instance, methods.getOrDefault("Before", List.of()), "@Before");
                test.invoke(instance);
                System.out.println("Test PASSED");
                successful++;
            } catch (Exception e) {
                System.err.println("Test FAILED: " + e.getCause());
                failed++;
            } finally {
                runMethods(instance, methods.getOrDefault("After", List.of()), "@After");
            }
        }
        return new Statistic(successful, failed);
    }

    private void runMethods(Object instance, List<Method> methods, String stage) {
        for (Method method : methods) {
            try {
                method.invoke(instance);
            } catch (Exception e) {
                System.err.println(stage + " method " + method.getName() + " failed: " + e.getCause());
            }
        }
    }

    private Object createInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create test instance", e);
        }
    }

    private Map<String, List<Method>> analyseMethodsOfClass(Class<?> clazz) {
        final Map<String, List<Method>> methods = new HashMap<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                methods.computeIfAbsent("Before", k -> new ArrayList<>()).add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                methods.computeIfAbsent("Test", k -> new ArrayList<>()).add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                methods.computeIfAbsent("After", k -> new ArrayList<>()).add(method);
            }
        }
        return methods;
    }
}
