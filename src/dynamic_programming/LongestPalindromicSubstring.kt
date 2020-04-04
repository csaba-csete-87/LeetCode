package dynamic_programming

import org.junit.Test
import kotlin.test.assertEquals

class LongestPalindromicSubstring {

    fun longestPalindrome(s: String): String {
        if (s.length < 2) {
            return s
        }
        if (s.length < 3) {
            return if (s[0] == s[1]) {
                s
            } else {
                s[0].toString()
            }
        }
        var longestPalindrome = s[0].toString()
        for (i in 1 until s.length) {
            val p1 = s[i - 1]
            val p2 = s[i]
            if (p1 == p2) {
                val p = getLongestExpandablePalindromeFromStartAndEnd(i - 1, i, s)
                if (p.length > longestPalindrome.length) {
                    longestPalindrome = p
                }
            }
        }
        for (i in 1 until s.length - 1) {
            val p1 = s[i - 1]
            val p2 = s[i + 1]
            if (p1 == p2) {
                val p = getLongestExpandablePalindromeFromStartAndEnd(i - 1, i + 1, s)
                if (p.length > longestPalindrome.length) {
                    longestPalindrome = p
                }
            }
        }
        return longestPalindrome
    }

    private fun getLongestExpandablePalindromeFromStartAndEnd(i: Int, j: Int, s: String): String {
        var n = i
        var m = j
        var pal = s.substring(n, m + 1)
        if (pal == s) {
            return pal
        }
        while (n >= 0 && m < s.length) {
            val newPal = s.substring(n, m + 1)
            if (isPalindrome(newPal) && newPal.length > pal.length) {
                pal = newPal
            }
            if (n > 0) {
                n--
            }
            m++
        }
        return pal
    }

    private fun isPalindrome(pal: String): Boolean {
        for (i in 0 until pal.length / 2) {
            if (pal[i] != pal[pal.length - i - 1]) {
                return false
            }
        }
        return true
    }

    @Test
    fun runTests() {
        assertEquals("aaabaaa", longestPalindrome("aaabaaaa"))
        assertEquals("a", longestPalindrome("abcda"))
        assertEquals("aaaa", longestPalindrome("aaaa"))
        assertEquals("bb", longestPalindrome("abb"))
        assertEquals("bab", longestPalindrome("babad"))
        assertEquals("bb", longestPalindrome("cbbd"))
        assertEquals("a", longestPalindrome("ac"))
        assertEquals("aa", longestPalindrome("aa"))
        assertEquals("ccc", longestPalindrome("ccc"))
    }
}