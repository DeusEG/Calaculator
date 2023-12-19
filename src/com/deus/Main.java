package com.deus;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var calculator = new Calculator();
        var converter = new Converter();
        System.out.println("Введите - '1', если хотите самостоятельно ввести выыражение");
        System.out.println("Введите - '2', чтобы увидеть вычисление заготовленных выражений");
        var userChoice = scanner.nextLine();


        if (userChoice.equals("1")) {
            System.out.println("Введите ваше выражение: ");
            var expression = converter.infixToPostfix(scanner.nextLine());
            System.out.println(calculator.postfixToEvaluation(expression));
        } else if (userChoice.equals("2")){
            var expression1 = converter.infixToPostfix("8 + 3 * 1");
            System.out.println(calculator.postfixToEvaluation(expression1));

            var expression2 = converter.infixToPostfix("(2 + 3) * 4");
            System.out.println(calculator.postfixToEvaluation(expression2));

            var expression3 = converter.infixToPostfix("3 * (4 + 5) - 6 / (1 + 2)");
            System.out.println(calculator.postfixToEvaluation(expression3));
        }
    }
}
