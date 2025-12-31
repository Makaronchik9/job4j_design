package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static String ret(int number) {
        String rsl;
        if (number % 3 == 0 && number % 5 == 0) {
            rsl = "FizzBuzz";
        } else if (number % 3 == 0) {
            rsl = "Fizz";
        } else if (number % 5 == 0) {
            rsl = "Buzz";
        } else {
            rsl = String.valueOf(number);
        }
        return rsl;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        int startAt = 1;
        Scanner io = new Scanner(System.in);
        while (startAt < 100) {
            String compAnswer = ret(startAt);
            System.out.println(compAnswer);
            startAt++;
            String userAnswer = io.nextLine();
            String expectedAnswer = ret(startAt);
            if (!userAnswer.equalsIgnoreCase(expectedAnswer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 1;
            } else {
                startAt++;
            }
        }
    }
}