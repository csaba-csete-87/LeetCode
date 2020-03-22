import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class OddEvenJump {

    fun oddEvenJumps(a: IntArray): Int {
        val treeMap = TreeMap<Int, Int>()
        val size = a.size
        val odds = BooleanArray(size)
        val evens = BooleanArray(size)
        odds[size - 1] = true
        evens[size - 1] = true
        treeMap[a[size - 1]] = size - 1
        for (i in size - 2 downTo 0) {
            val v = a[i]
            if (treeMap.containsKey(v)) {
                val index = treeMap[v]!!
                odds[i] = evens[index]
                evens[i] = odds[index]
            } else {
                val lower = treeMap.lowerKey(v)
                if (lower != null) {
                    val lowerIndex = treeMap[lower]!!
                    evens[i] = odds[lowerIndex]
                }

                val higher = treeMap.higherKey(v)
                if (higher != null) {
                    val higherIndex = treeMap[higher]!!
                    odds[i] = evens[higherIndex]
                }
            }
            treeMap[v] = i
        }
        return odds.count { it }
    }

    @Test
    fun runTest() {
        assertEquals(2, oddEvenJumps(intArrayOf(10, 13, 12, 14, 15)))
        assertEquals(3, oddEvenJumps(intArrayOf(2, 3, 1, 1, 4)))
        assertEquals(3, oddEvenJumps(intArrayOf(5, 1, 3, 4, 2)))
        assertEquals(5, oddEvenJumps(intArrayOf(2, 3, 1, 1, 4, 2, 3, 1, 1, 4)))
    }
}