package trees_graphs

import org.junit.Assert
import org.junit.Test

class DecodeString {
    val zero = '0'
    val nine = '9'

    fun decodeString(s: String): String {
        var i = 0
        var nr = ""
        val sb = StringBuilder()
        var isContentMode = false
        var content = ""
        var opened = 0
        while (i < s.length) {
            val c = s[i]
            if (!isContentMode && c in zero..nine) {
                nr = nr.plus(c)
            } else if (c == '[') {
                if (!isContentMode) {
                    isContentMode = true
                } else {
                    content = content.plus(c)
                }
                opened++
            } else if (c == ']') {
                opened--
                if (opened == 0) {
                    val m = nr.toInt()
                    val decoded = decodeString(content)
                    for (j in 1..m) {
                        sb.append(decoded)
                    }
                    nr = ""
                    isContentMode = false
                    content = ""
                } else {
                    content = content.plus(c)
                }
            } else {
                if (isContentMode) {
                    content = content.plus(c)
                } else {
                    sb.append(c)
                }
            }
            i++
        }
        return sb.toString()
    }

    @Test
    fun runTests() {
        Assert.assertEquals("accaccacc", decodeString("3[a2[c]]"))
        Assert.assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"))
        Assert.assertEquals("dxyabcabcabcabcabcabcabcabcabcabcabcabc", decodeString("dxy12[abc]"))
        Assert.assertEquals("aaabcbc", decodeString("3[a]2[bc]"))
    }
}