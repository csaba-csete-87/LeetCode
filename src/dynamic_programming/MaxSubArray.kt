package dynamic_programming

import org.junit.Test
import kotlin.test.assertEquals

class MaxSubArray {

    fun maxSubArray(nums: IntArray): Int {
        var maxSum = nums[0]
        var crtSum = nums[0]
        for (i in 1 until nums.size) {
            val crt = nums[i]
            crtSum = Math.max(crt, crtSum + crt)
            maxSum = Math.max(crtSum, maxSum)
        }
        return maxSum
    }

    @Test
    fun runTests() {
        assertEquals(6, maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    }
}