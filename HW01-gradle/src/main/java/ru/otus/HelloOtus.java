package ru.otus;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

public class HelloOtus {
    public static void main(String[] args) {
        ImmutableList<String> colors = ImmutableList.of("Red", "Green", "Blue");
        System.out.println("Colors: " + colors);

        int value = 10;
        Preconditions.checkArgument(value > 0, "Value must be positive: %s", value);

        String padded = Strings.padStart("123", 5, '0');
        System.out.println("Padded: " + padded);
    }
}
