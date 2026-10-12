package stacksandqueues;

public final class ValidateProperlyNestedBracketsTest {
    private ValidateProperlyNestedBracketsTest() {
    }

    public static void main(String[] args) {
        assertValid("nested bracket types", "{[()]}", true);
        assertValid("separate bracket groups", "(){}[]", true);
        assertValid("empty string", "", true);
        assertValid("ignore non-bracket characters", "text [a + (b)]", true);
        assertValid("null input", null, false);
        assertValid("unmatched opening", "((", false);
        assertValid("unmatched closing", "())", false);
        assertValid("mismatched types", "([)]", false);
        assertValid("incorrect nesting", "{[(])}", false);
        System.out.println("All ValidateProperlyNestedBrackets tests passed.");
    }

    private static void assertValid(String caseName, String input, boolean expected) {
        boolean actual = ValidateProperlyNestedBrackets.validateBrackets(input);
        if (actual != expected) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}