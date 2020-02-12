import org.junit.Test
import kotlin.math.max
import kotlin.test.assertEquals

class MinimumDominoRotations {

    private fun minDominoRotations(a: IntArray, b: IntArray): Int {
        val r = getMinRotationsForValue(a, b, a[0])
        return if (r == -1) {
            getMinRotationsForValue(a, b, b[0])
        } else {
            r
        }
    }

    private fun getMinRotationsForValue(top: IntArray, bot: IntArray, value: Int): Int {
        var topR = 0
        var botR = 0
        for (i in top.indices) {
            if (top[i] != value && bot[i] != value) {
                return -1
            }
            if (top[i] == value) topR++
            if (bot[i] == value) botR++
        }
        return top.size - max(topR, botR)
    }

    @Test
    fun runTest() {
        val expected1 = 2
        val result1 = minDominoRotations(
            intArrayOf(2, 1, 2, 4, 2, 2),
            intArrayOf(5, 2, 6, 2, 3, 2)
        )
        assertEquals(expected1, result1)

        val expected2 = -1
        val result2 = minDominoRotations(
            intArrayOf(3, 5, 1, 2, 3),
            intArrayOf(3, 6, 3, 3, 4)
        )
        assertEquals(expected2, result2)
    }
}