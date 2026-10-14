package stringsandpatterns;

public final class PalindromeByFilteringNonLetters {
    private PalindromeByFilteringNonLetters() {
    }

    public static boolean isPalindrome(String input) {
        int[] letters = input.codePoints()
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .toArray();

        for (int left = 0, right = letters.length - 1; left < right; left++, right--) {
            if (letters[left] != letters[right]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String input = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(input));
    }
}