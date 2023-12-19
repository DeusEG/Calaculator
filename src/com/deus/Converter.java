package com.deus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Converter {
    // print and burn
    public String infixToPostfix(String infixExpression) {
        infixExpression = infixExpression.replaceAll(" ", "");
        var stack = new Stack<Character>();
        StringBuilder postfix = new StringBuilder();
        var charArray = infixExpression.toCharArray();

        for(char token: charArray) {
            if(token != '+' && token != '-' && token != '*' && token != '/' && token != '(' && token != ')') {
                postfix.append(token);
            } else if (token == '(') {
                stack.push(token);
            } else if (token == ')') {
                while(!stack.isEmpty()) {
                    char tempToken = stack.pop();
                    if(tempToken != '(') {
                        postfix.append(tempToken);
                    } else {
                        break;
                    }
                }
            } else {
                if(stack.isEmpty()) {
                    stack.push(token);
                } else {
                    while(!stack.isEmpty()) {
                        char tempToken = stack.pop();
                        if(tempToken == '(') {
                            stack.push(tempToken);
                            break;
                        } else if(tempToken == '+' || tempToken == '-' || tempToken == '*' || tempToken == '/') {
                            if(getPriority(tempToken) <  getPriority(token)) {
                                stack.push(tempToken);
                                break;
                            } else {
                                postfix.append(tempToken);
                            }
                        }
                    }
                    stack.push(token);
                }
            }
        }
        while(!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    public Integer getPriority(char token) {
        var priorityOperations = new HashMap<Integer, List<Character>>();
        priorityOperations.put(1, List.of('+', '-'));
        priorityOperations.put(2, List.of('*', '/'));

        for (Map.Entry<Integer, List<Character>> entry : priorityOperations.entrySet()) {
            for (Character operation: entry.getValue()) {
                if (operation == token) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
}
