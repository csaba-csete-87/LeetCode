package arrays_strings

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.collections.HashMap

class ValidParentheses {

    val open = HashMap<Char, Char>()
    val close = HashMap<Char, Char>()

    init {
        open['('] = ')'
        open['['] = ']'
        open['{'] = '}'
        close[')'] = '('
        close[']'] = '['
        close['}'] = '{'
    }

    fun isValid(s: String): Boolean {
        val queue = LinkedList<Char>()
        for (c in s) {
            if (c in open.keys) {
                queue.addLast(c)
            } else if (queue.isNotEmpty() && queue.last() == close[c]) {
                queue.removeLast()
            } else {
                return false
            }
        }
        return queue.isEmpty()
    }

    @Test
    fun runTests() {
        assertFalse(isValid("]"))
        assertTrue(isValid("()"))
        assertTrue(isValid("()[]{}"))
        assertFalse(isValid("(]"))
        assertFalse(isValid("([)]"))
        assertTrue(isValid("{[]}"))
        assertTrue(isValid("{{}[][[[]]]}"))
    }
}