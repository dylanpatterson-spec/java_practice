package stringsandpatterns;

import java.util.List;

public final class SmallestSubstringContainingAllPatternsTest {
    private SmallestSubstringContainingAllPatternsTest() {
    }

    public static void main(String[] args) {
        assertWindow("smallest window with overlapping matches", List.of(6, 8),
                List.of("ab", "bc"), "abxxbcabc");
        assertWindow("overlapping pattern occurrences", List.of(0, 3),
                List.of("aba", "bab"), "ababa");
        assertWindow("single pattern", List.of(2, 3),
                List.of("ab"), "xxabxxxab");
        assertWindow("missing pattern", List.of(-1, -1),
                List.of("ab", "d"), "abc");
        assertWindow("empty patterns", List.of(0, 0), List.of(), "abc");
        assertWindow("empty text", List.of(-1, -1), List.of("a"), "");
        System.out.println("All SmallestSubstringContainingAllPatterns tests passed.");
    }

    private static void assertWindow(String caseName, List<Integer> expected, List<String> patterns, String S) {
        List<Integer> actual = SmallestSubstringContainingAllPatterns.findSmallestWindow(patterns, S);
        if (!expected.equals(actual)) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}