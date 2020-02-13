import org.junit.Test
import kotlin.test.assertEquals

class CheckIfNAndItsDoubleExists {


    fun checkIfExist(arr: IntArray): Boolean {
        for (i in 0 until arr.size - 1) {
            val m = arr[i]
            for (j in i + 1 until arr.size) {
                val n = arr[j]

                if (m == 0 && n == 0) {
                    return true
                }
                if ((m == n * 2 || m * 2 == n) && m != 0 && n != 0) {
                    return true
                }
            }
        }
        return false
    }

    @Test
    fun runTests() {
        assertEquals(true, checkIfExist(intArrayOf(10, 2, 5, 3)))
        assertEquals(true, checkIfExist(intArrayOf(7, 1, 14, 11)))
        assertEquals(false, checkIfExist(intArrayOf(3, 1, 7, 11)))
        assertEquals(false, checkIfExist(intArrayOf(-2, 0, 10, -19, 4, 6, -8)))
        assertEquals(true, checkIfExist(intArrayOf(0, 0)))
    }
}