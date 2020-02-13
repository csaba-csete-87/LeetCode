import org.junit.Test
import kotlin.math.abs
import kotlin.test.assertEquals

class MinNrOfStepsToMakeTwoStringsAnagram {
    fun minSteps(s: String, t: String): Int {
        if (s == t) {
            return 0
        }
        val a = 'a'.toInt()
        val size = 26 //lowercase letters in English language
        val sA = IntArray(size)
        val tA = IntArray(size)

        for (i in s.indices) {
            sA[s[i].toInt() - a]++
            tA[t[i].toInt() - a]++
        }

        var steps = 0
        for (i in 0 until size) {
            steps += abs(sA[i] - tA[i])
        }

        return steps / 2
    }

    @Test
    fun runTests() {
        assertEquals(1, minSteps("bab", "aba"))
        assertEquals(5, minSteps("leetcode", "practice"))
        assertEquals(0, minSteps("anagram", "mangaar"))
        assertEquals(0, minSteps("xxyyzz", "xxyyzz"))
        assertEquals(4, minSteps("friend", "family"))
    }
}