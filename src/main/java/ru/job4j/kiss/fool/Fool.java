package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        int startAt = 1;
        Scanner input = new Scanner(System.in);

        while (startAt < 100) {
            System.out.println(FizzBuzz.value(startAt));
            String answer = input.nextLine();

            if (!FizzBuzz.value(startAt + 1).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}
