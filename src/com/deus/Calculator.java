package com.deus;

import java.util.*;
import java.util.function.BinaryOperator;

public class Calculator {
    Stack<Character> stackOperation = new Stack<>();
    Stack<Double> stackDigit = new Stack<>();

    BinaryOperator<Double> addition = (x, y) -> x + y;
    BinaryOperator<Double> subtraction = (x, y) -> x - y;
    BinaryOperator<Double> multiplication = (x, y) -> x * y;
    BinaryOperator<Double> division = (x, y) -> x / y;

    public Integer getOperationPriority(Character operation) {
        var priorityOperations = new HashMap<Integer, List<Character>>();
        priorityOperations.put(0, List.of('(', ')'));
        priorityOperations.put(1, List.of('+', '-'));
        priorityOperations.put(2, List.of('*', '/'));
        priorityOperations.put(3, List.of('^'));

        for (Map.Entry<Integer, List<Character>> entry : priorityOperations.entrySet()) {
            for (Character oper : entry.getValue()) {
                if (oper == operation) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public Double calculateExpression(char[] charArray) {

        for (char token: charArray) {
            if (Character.isDigit(token)) {
                stackDigit.push((double) Character.getNumericValue(token));
            }
            else {
                while (!stackOperation.empty() && (getOperationPriority(token) <= getOperationPriority(stackOperation.peek()))) {
                    calculate();
                }
                stackOperation.push(token);
            }
        }

        return stackDigit.peek();
    }

    public void calculate() {
        var secondOperand = stackDigit.pop();
        var firstOperand = stackDigit.pop();
        stackDigit.push(calculateOperands(secondOperand, firstOperand, stackOperation.peek()));
    }

    public char[] convertExpression(String expression) {
        expression = expression.replaceAll(" ", "");
        return expression.toCharArray();
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
