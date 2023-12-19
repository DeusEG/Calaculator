package com.deus;

import java.util.*;
import java.util.function.BinaryOperator;

public class Calculator {
    BinaryOperator<Double> addition = Double::sum;
    BinaryOperator<Double> subtraction = (x, y) -> x - y;
    BinaryOperator<Double> multiplication = (x, y) -> x * y;
    BinaryOperator<Double> division = (x, y) -> x / y;

    public double postfixToEvaluation(String postfixExpression) {
        var stack = new Stack<Double>();
        Double firstOperand;
        Double secondOperand;
        var charArray = postfixExpression.toCharArray();
        for(char token: charArray) {
            if(token >= '0' && token <= '9') {
                stack.push((double)(token - '0'));
            } else {
                secondOperand = stack.pop();
                firstOperand = stack.pop();
                stack.push(calculateOperands(firstOperand, secondOperand, token));
            }
        }
        return stack.pop();
    }

    public double calculateOperands(double a, double b, char operation) {
        switch (operation) {
            case '+':
                return addition.apply(a, b);
            case '-':
                return subtraction.apply(a, b);
            case '*':
                return multiplication.apply(a, b);
            case '/':
                if (b == 0) {
                    throw new IllegalArgumentException("Divide by zero.");
                }
                return division.apply(a, b);

            default:
                throw new UnsupportedOperationException();
        }
    }
}
