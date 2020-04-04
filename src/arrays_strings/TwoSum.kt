package arrays_strings

import org.junit.Assert
import org.junit.Test

class TwoSum {

    private fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, ArrayList<Int>>()
        for(i in 0 until nums.size)
        nums.forEachIndexed { i, n ->
            if (map.contains(n)) {
                map[n]?.add(i)
            } else {
                map[n] = arrayListOf(i)
            }
        }
        nums.forEachIndexed { i, a ->
            val b = target - a
            if (a == b && map[a]?.size == 2) {
                return map[a]?.toIntArray() ?: intArrayOf()
            } else if (a != b && map.contains(b)) {
                return intArrayOf(i, map[b]?.get(0) ?: 0)
            }
        }
        throw IllegalArgumentException("No two sum solution")
    }

    @Test
    fun runTest() {
        val expected1 = intArrayOf(0, 1)
        val result1 = twoSum(intArrayOf(2, 7, 11, 15), 9)
        Assert.assertTrue(expected1.contentEquals(result1))

        val expected2 = intArrayOf(1, 2)
        val result2 = twoSum(intArrayOf(3, 2, 4), 6)
        Assert.assertTrue(expected2.contentEquals(result2))
    }
}