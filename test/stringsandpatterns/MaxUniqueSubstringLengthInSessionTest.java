package stringsandpatterns;

public final class MaxUniqueSubstringLengthInSessionTest {
    private MaxUniqueSubstringLengthInSessionTest() {
    }

    public static void main(String[] args) {
        assertLength("repeated letters", 3, "abcabcbb");
        assertLength("maximum across sessions", 6, "abcabcbb*abcdef");
        assertLength("substrings cannot cross sessions", 3, "abc*def");
        assertLength("empty sessions", 0, "**");
        assertLength("empty input", 0, "");
        System.out.println("All MaxUniqueSubstringLengthInSession tests passed.");
    }

    private static void assertLength(String caseName, int expected, String sessions) {
        int actual = MaxUniqueSubstringLengthInSession.maxUniqueSubstringLength(sessions);
        if (actual != expected) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}