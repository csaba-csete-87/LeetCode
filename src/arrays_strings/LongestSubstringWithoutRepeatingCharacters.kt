package arrays_strings

import org.junit.Test
import kotlin.test.assertEquals

class LongestSubstringWithoutRepeatingCharacters {

    fun lengthOfLongestSubstring(s: String): Int {
        var set: HashMap<Char, Int> = hashMapOf()
        var max = 0
        var i = 0
        var j = 0
        while (i < s.length && j < s.length) {
            if (set.containsKey(s[j])) {
                set.remove(s[i])
                i++
            } else {
                set.put(s[j], j)
                j++
                max = Math.max(max, j - i)
            }
        }
        return max
    }

    @Test
    fun runTests() {
        assertEquals(5, lengthOfLongestSubstring("tmmzuxt"))
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"))
        assertEquals(1, lengthOfLongestSubstring("bbbbb"))
        assertEquals(3, lengthOfLongestSubstring("pwwkew"))
        assertEquals(3, lengthOfLongestSubstring("dvdf"))
    }
}