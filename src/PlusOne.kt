import org.junit.Test

class PlusOne {

    fun plusOne(digits: IntArray): IntArray {
        val firstSmallerThan9 = digits.firstOrNull { it != 9 }
        if (firstSmallerThan9 == null) {
            val easyResult = IntArray(digits.size + 1)
            easyResult[0] = 1
            return easyResult
        }
        val result = IntArray(digits.size)
        var carry = 0
        for (i in digits.size - 1 downTo 0) {
            val d = digits[i]
            if (i == digits.size - 1) {
                val last = d + 1
                if (last > 9) {
                    carry = 1
                    result[i] = 0
                } else {
                    result[i] = last
                }
            } else {
                val nr = d + carry
                if (nr > 9) {
                    result[i] = 0
                } else {
                    result[i] = nr
                    carry = 0
                }
            }
        }
        return result
    }

    @Test
    fun runTests() {

    }
}