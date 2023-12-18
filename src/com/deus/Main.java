package com.deus;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String expression = scanner.nextLine();
        Calculator calc = new Calculator();
        var ex = calc.convertExpression("3 + 2 / 2 * 2");
        System.out.println(calc.calculateExpression(ex));
        System.out.println(calc.stackOperation);
        System.out.println(calc.stackDigit);

    }
}
