package arrays_strings

import org.junit.Test
import kotlin.math.pow
import kotlin.test.assertEquals

class MultiplyStrings {
    fun multiply(num1: String, num2: String): String {
        val zero = "0"[0].toInt()
        var result = 0L
        for (j in num2.length - 1 downTo 0) {
            var parts = 0L
            val nr = num2[j].toInt() - zero
            var carry = 0
            for (i in num1.length - 1 downTo 0) {
                val crt = num1[i].toInt() - zero
                val prod = crt * nr + carry
                val strippedProd = if (prod > 10) {
                    carry = prod / 10
                    prod % 10
                } else {
                    carry = 0
                    prod
                }
                parts += (strippedProd % 10) * 10.toDouble().pow(num1.length - 1 - i).toLong()
            }
            if (carry > 0) {
                var n = ""
                for (x in 1..parts.toString().length) {
                    n = n.plus("0")
                }
                val y = carry.toString().plus(n).toLong()
                parts += y
            }
            var m = ""
            for (k in 1..num2.length - 1 - j) {
                m = m.plus("0")
            }
            val p = parts.toString().plus(m)
            result += p.toLong()
        }
        return result.toString()
    }

    //121932631112635269
    //121932630112635269
    //8991
    //
    //
    @Test
    fun runTests() {
        assertEquals("80779853376", multiply("123456", "654321"))
        assertEquals("121932631112635269", multiply("123456789", "987654321"))
        assertEquals("998001", multiply("999", "999"))
        assertEquals("882", multiply("98", "9"))
        assertEquals("56088", multiply("123", "456"))
        assertEquals("81", multiply("9", "9"))
        assertEquals("6", multiply("2", "3"))
    }
}