package stacksandqueues;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ValidateProperlyNestedBrackets {
    private ValidateProperlyNestedBrackets() {
    }

    public static boolean validateBrackets(String input) {
        if (input == null) {
            return false;
        }

        Deque<Character> openings = new ArrayDeque<>();
        for (int index = 0; index < input.length(); index++) {
            char character = input.charAt(index);
            if (character == '(' || character == '{' || character == '[') {
                openings.push(character);
            } else if (character == ')' || character == '}' || character == ']') {
                if (openings.isEmpty() || !matches(openings.pop(), character)) {
                    return false;
                }
            }
        }
        return openings.isEmpty();
    }

    private static boolean matches(char opening, char closing) {
        return opening == '(' && closing == ')'
                || opening == '{' && closing == '}'
                || opening == '[' && closing == ']';
    }

    public static void main(String[] args) {
        System.out.println(validateBrackets("{[()]}"));
    }
}