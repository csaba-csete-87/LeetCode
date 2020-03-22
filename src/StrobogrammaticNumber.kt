import org.junit.Test
import kotlin.test.assertEquals

class StrobogrammaticNumber {

    fun isStrobogrammatic(num: String): Boolean {
        if (num[0] == '0') return false

        val complements: HashMap<Char, Char> = hashMapOf()
        complements['0'] = '0'
        complements['1'] = '1'
        complements['6'] = '9'
        complements['8'] = '8'
        complements['9'] = '6'

        for (i in num.indices) {
            val c = num[i]
            if (!complements.contains(c)) {
                return false
            } else if (c != complements[num[num.length - 1 - i]]) {
                return false
            }
        }
        return true
    }

    @Test
    fun runTests() {
        assertEquals(true, isStrobogrammatic("69"))
        assertEquals(true, isStrobogrammatic("11"))
        assertEquals(true, isStrobogrammatic("88"))
        assertEquals(false, isStrobogrammatic("962"))
    }
}