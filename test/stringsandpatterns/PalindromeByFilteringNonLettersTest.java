package stringsandpatterns;

public final class PalindromeByFilteringNonLettersTest {
    private PalindromeByFilteringNonLettersTest() {
    }

    public static void main(String[] args) {
        assertPalindrome("letters ignore case and symbols", true, "A man, a plan, a canal: Panama");
        assertPalindrome("digits are ignored", true, "1Race, car2");
        assertPalindrome("non-palindrome letters", false, "ab,ca");
        assertPalindrome("no letters", true, "123 !?");
        assertPalindrome("unicode letters", true, "Été!");
        System.out.println("All PalindromeByFilteringNonLetters tests passed.");
    }

    private static void assertPalindrome(String caseName, boolean expected, String input) {
        boolean actual = PalindromeByFilteringNonLetters.isPalindrome(input);
        if (actual != expected) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}