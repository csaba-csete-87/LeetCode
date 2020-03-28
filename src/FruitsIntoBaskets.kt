import org.junit.Test
import java.util.*
import kotlin.collections.HashMap
import kotlin.test.assertEquals


class FruitsIntoBaskets {

    fun totalFruitAnswer(tree: IntArray): Int {
        val counter = HashMap<Int, Int>()
        var ans = 0
        var i = 0
        val count = Counter()
        for (j in tree.indices) {
            count.add(tree[j], 1)
            while (count.size >= 3) {
                count.add(tree[i], -1)
                if (count[tree[i]] == 0)
                    count.remove(tree[i])
                i++
            }

            ans = Math.max(ans, j - i + 1)
        }

        return ans
    }

    internal inner class Counter : HashMap<Int, Int>() {
        override fun get(k: Int): Int {
            return if (containsKey(k)) super.get(k) ?: 0 else 0
        }

        fun add(k: Int, v: Int) {
            put(k, get(k) + v)
        }
    }

    @Test
    fun runTests() {
        assertEquals(3, totalFruitAnswer(intArrayOf(1, 2, 1)))
        assertEquals(3, totalFruitAnswer(intArrayOf(0, 1, 2, 2)))
        assertEquals(4, totalFruitAnswer(intArrayOf(1, 2, 3, 2, 2)))
        assertEquals(5, totalFruitAnswer(intArrayOf(3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4)))
        assertEquals(2, totalFruitAnswer(intArrayOf(1, 1)))
        assertEquals(5, totalFruitAnswer(intArrayOf(1, 0, 1, 4, 1, 4, 1, 2, 3)))
        assertEquals(1, totalFruitAnswer(intArrayOf(1)))
        assertEquals(2, totalFruitAnswer(intArrayOf(0, 1, 2)))
        assertEquals(3, totalFruitAnswer(intArrayOf(0, 1, 1, 2)))
        assertEquals(4, totalFruitAnswer(intArrayOf(0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5)))
    }
}