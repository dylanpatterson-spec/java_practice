package stringsandpatterns;

public final class MaxUniqueSubstringLengthInSession {
    private MaxUniqueSubstringLengthInSession() {
    }

    public static int maxUniqueSubstringLength(String sessions) {
        int[] lastSeen = new int[26];
        java.util.Arrays.fill(lastSeen, -1);
        int windowStart = 0;
        int maximumLength = 0;

        for (int index = 0; index < sessions.length(); index++) {
            char character = sessions.charAt(index);
            if (character == '*') {
                windowStart = index + 1;
                continue;
            }

            int letterIndex = character - 'a';
            windowStart = Math.max(windowStart, lastSeen[letterIndex] + 1);
            lastSeen[letterIndex] = index;
            maximumLength = Math.max(maximumLength, index - windowStart + 1);
        }

        return maximumLength;
    }

    public static void main(String[] args) {
        System.out.println(maxUniqueSubstringLength("abcabcbb*abcdef"));
    }
}