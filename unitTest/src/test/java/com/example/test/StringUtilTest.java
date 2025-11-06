package com.example.test;

import com.example.StringUtils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StringUtils Tests")
class StringUtilsTest {

    private StringUtils stringUtils;

    @BeforeEach
    void setUp() {
        stringUtils = new StringUtils();
    }

    @Nested
    @DisplayName("Palindrome Tests")
    class PalindromeTests {

        @ParameterizedTest(name = "'{0}' should be identified as a palindrome")
        @ValueSource(strings = {
                "madam", "racecar", "level", "noon", "radar",
                "civic", "rotor", "kayak", "deified", "stats", "a", ""
        })
        void testValidPalindromes(String palindrome) {
            assertTrue(stringUtils.isPalindrome(palindrome));
        }

        @ParameterizedTest(name = "'{0}' should NOT be identified as a palindrome")
        @ValueSource(strings = {
                "hello", "world", "java", "testing", "palindrome", "abc", "abcd"
        })
        void testNonPalindromes(String nonPalindrome) {
            assertFalse(stringUtils.isPalindrome(nonPalindrome));
        }

        @ParameterizedTest(name = "Case insensitive: '{0}' should be a palindrome")
        @ValueSource(strings = {
                "Madam", "RaceCar", "Level", "NOON", "MaDaM"
        })
        void testCaseInsensitivePalindromes(String palindrome) {
            assertTrue(stringUtils.isPalindrome(palindrome));
        }

        @Test
        void testPalindromesWithSpaces() {
            assertTrue(stringUtils.isPalindrome("a man a plan a canal panama"));
            assertTrue(stringUtils.isPalindrome("racecar racecar"));
            assertTrue(stringUtils.isPalindrome("was it a rat i saw"));
        }

        @Test
        void testNullPalindrome() {
            assertFalse(stringUtils.isPalindrome(null));
        }

        @ParameterizedTest(name = "Testing palindrome: '{0}' = {1}")
        @CsvSource({
                "'madam', true",
                "'hello', false",
                "'level', true",
                "'world', false",
                "'a', true",
                "'ab', false",
                "'aa', true"
        })
        void testPalindromesParameterized(String input, boolean expected) {
            assertEquals(expected, stringUtils.isPalindrome(input));
        }
    }

    @Nested
    @DisplayName("String Reverse Tests")
    class ReverseTests {

        @Test
        void testReverseNormalString() {
            assertEquals("olleh", stringUtils.reverse("hello"));
            assertEquals("dlrow", stringUtils.reverse("world"));
            assertEquals("avaj", stringUtils.reverse("java"));
        }

        @Test
        void testReverseSingleCharacter() {
            assertEquals("a", stringUtils.reverse("a"));
            assertEquals("Z", stringUtils.reverse("Z"));
        }

        @Test
        void testReverseEmptyString() {
            assertEquals("", stringUtils.reverse(""));
        }

        @Test
        void testReverseNull() {
            assertNull(stringUtils.reverse(null));
        }

        @Test
        void testReverseWithSpacesAndSpecialChars() {
            assertEquals("!dlrow ,olleH", stringUtils.reverse("Hello, world!"));
            assertEquals("  ecaps  ", stringUtils.reverse("  space  "));
            assertEquals("321@#$", stringUtils.reverse("$#@123"));
        }

        @ParameterizedTest(name = "Reversing '{0}' should give '{1}'")
        @CsvSource({
                "'hello', 'olleh'",
                "'abc', 'cba'",
                "'12345', '54321'",
                "'A', 'A'",
                "'', ''",
                "'Hello World', 'dlroW olleH'"
        })
        void testReverseParameterized(String input, String expected) {
            assertEquals(expected, stringUtils.reverse(input));
        }
    }

    @Nested
    @DisplayName("IsBlank Tests")
    class IsBlankTests {

        @Test
        void testNullIsBlank() {
            assertTrue(stringUtils.isBlank(null));
        }

        @Test
        void testEmptyIsBlank() {
            assertTrue(stringUtils.isBlank(""));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                " ", "  ", "\t", "\n", "\r", " \t \n \r ", "   \t   "
        })
        void testWhitespaceIsBlank(String whitespaceString) {
            assertTrue(stringUtils.isBlank(whitespaceString));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "hello", " hello ", "a", " a ", "\thello\n", "hello world"
        })
        void testNonBlankStrings(String nonBlankString) {
            assertFalse(stringUtils.isBlank(nonBlankString));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void testIsBlankComprehensive(String input) {
            assertTrue(stringUtils.isBlank(input));
        }

        @Test
        void testSpacesOnlyIsBlank() {
            assertTrue(stringUtils.isBlank("     "));
            assertTrue(stringUtils.isBlank(" "));
            assertTrue(stringUtils.isBlank("  "));
        }
    }

    @Nested
    @DisplayName("Edge Cases and Integration Tests")
    class EdgeCasesTests {

        @Test
        void testVeryLongStrings() {
            String longString = "a".repeat(1000);
            String expectedReverse = "a".repeat(1000);
            assertEquals(expectedReverse, stringUtils.reverse(longString));
            assertTrue(stringUtils.isPalindrome(longString));
            assertFalse(stringUtils.isBlank(longString));
        }

        @Test
        void testUnicodeCharacters() {
            assertEquals("🌟⭐", stringUtils.reverse("⭐🌟"));
            assertFalse(stringUtils.isBlank("🌟"));
            assertFalse(stringUtils.isPalindrome("🌟🌟"));
        }

        @Test
        void testNullHandling() {
            assertNull(stringUtils.reverse(null));
            assertFalse(stringUtils.isPalindrome(null));
            assertTrue(stringUtils.isBlank(null));
        }
    }
}
