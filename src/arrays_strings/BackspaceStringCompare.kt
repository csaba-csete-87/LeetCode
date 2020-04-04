package arrays_strings

import junit.framework.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class BackspaceStringCompare {

    private fun backspaceCompare(s: String, t: String) = getTypedText(s) == getTypedText2(t)

    private fun getTypedText(input: String): String {
        var s = input
        var i = 0
        while (i < s.length) {
            if (s[i].toString() == "#") {
                if (i > 0) {
                    s = s.substring(0, i - 1) + s.substring(i + 1, s.length)
                    i--
                } else {
                    s = s.substring(i + 1, s.length)
                }
            } else {
                i++
            }
        }

        return s
    }

    private fun getTypedText2(input: String): String {
        var typedText = ""
        for (i in input.indices) {
            typedText = if (input[i].toString() == "#") {
                typedText.dropLast(1)
            } else {
                typedText.plus(input[i])
            }
        }
        return typedText
    }

    @Test
    fun runTest() {
        assertTrue(backspaceCompare("ab#c", "ad#c"))
        assertTrue(backspaceCompare("ab##", "c#d#"))
        assertTrue(backspaceCompare("a##c", "#a#c"))

        assertFalse(backspaceCompare("a#c", "b"))

        assertTrue(backspaceCompare("y#fo##f", "y#f#o##f"))
    }
}