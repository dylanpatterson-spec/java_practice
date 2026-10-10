package stringsandpatterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public final class SmallestSubstringContainingAllPatterns {
    private SmallestSubstringContainingAllPatterns() {
    }

    public static List<Integer> findSmallestWindow(List<String> patterns, String S) {
        if (S.isEmpty()) {
            return List.of(-1, -1);
        }

        List<String> requiredPatterns = new ArrayList<>();
        List<List<Integer>> occurrencesByPattern = new ArrayList<>();
        TreeSet<Integer> candidateStarts = new TreeSet<>();

        for (String pattern : patterns) {
            if (pattern == null) {
                throw new IllegalArgumentException("Patterns cannot contain null");
            }
            if (pattern.isEmpty()) {
                continue;
            }

            List<Integer> starts = new ArrayList<>();
            for (int start = S.indexOf(pattern); start >= 0; start = S.indexOf(pattern, start + 1)) {
                starts.add(start);
                candidateStarts.add(start);
            }
            if (starts.isEmpty()) {
                return List.of(-1, -1);
            }

            requiredPatterns.add(pattern);
            occurrencesByPattern.add(starts);
        }

        if (requiredPatterns.isEmpty()) {
            return List.of(0, 0);
        }

        int bestStart = -1;
        int bestEnd = -1;
        int bestLength = Integer.MAX_VALUE;

        for (int candidateStart : candidateStarts) {
            int candidateEnd = candidateStart;
            boolean coversAllPatterns = true;

            for (int patternIndex = 0; patternIndex < requiredPatterns.size(); patternIndex++) {
                List<Integer> starts = occurrencesByPattern.get(patternIndex);
                int occurrenceIndex = Collections.binarySearch(starts, candidateStart);
                if (occurrenceIndex < 0) {
                    occurrenceIndex = -occurrenceIndex - 1;
                }
                if (occurrenceIndex == starts.size()) {
                    coversAllPatterns = false;
                    break;
                }

                int occurrenceEnd = starts.get(occurrenceIndex) + requiredPatterns.get(patternIndex).length() - 1;
                candidateEnd = Math.max(candidateEnd, occurrenceEnd);
            }

            if (coversAllPatterns) {
                int candidateLength = candidateEnd - candidateStart + 1;
                if (candidateLength < bestLength) {
                    bestStart = candidateStart;
                    bestEnd = candidateEnd;
                    bestLength = candidateLength;
                }
            }
        }

        return List.of(bestStart, bestEnd);
    }

    public static void main(String[] args) {
        String S = "abxxbcabc";
        List<String> patterns = List.of("ab", "bc");
        System.out.println(findSmallestWindow(patterns, S));
    }
}