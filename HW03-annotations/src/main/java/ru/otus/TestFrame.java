package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import ru.otus.anotations.After;
import ru.otus.anotations.Before;
import ru.otus.anotations.Test;

public class TestFrame {
    private List<Method> befores = new ArrayList<>();
    private List<Method> tests = new ArrayList<>();
    private List<Method> afters = new ArrayList<>();
    int successful = 0;
    int failed = 0;

    public void start(Class<?> clazz) {
        analyseMethodsOfClass(clazz);
        executeTests(clazz);
        System.out.printf("\nSuccessfull: %d\nFailed: %d\n", successful, failed);
    }

    private void executeTests(Class<?> clazz) {
        for (Method test : tests) {
            try {
                System.out.println("\nRunning test: " + test.getName());
                Object instance = clazz.getDeclaredConstructor().newInstance();

                runMethods(instance, befores, "@Before");

                test.invoke(instance);
                System.out.println("Test PASSED");
                successful++;

                runMethods(instance, afters, "@After");

            } catch (Exception e) {
                System.err.println("Test FAILED: " + e.getCause());
                failed++;
            }
        }
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

    private void analyseMethodsOfClass(Class<?> clazz) {
        befores.clear();
        tests.clear();
        afters.clear();

        for (Method method : clazz.getDeclaredMethods()) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Before) {
                    befores.add(method);
                } else if (annotation instanceof Test) {
                    tests.add(method);
                } else if (annotation instanceof After) {
                    afters.add(method);
                }
            }
        }
    }
}
