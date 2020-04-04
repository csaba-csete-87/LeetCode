package arrays_strings

import org.junit.Assert.assertTrue
import org.junit.Test

class NextPermutation {

    fun nextPermutation(nums: IntArray): IntArray {
        var i = nums.size - 2
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--
        }
        if (i >= 0) {
            var j = nums.size - 1
            while (j >= 0 && nums[j] <= nums[i]) {
                j--
            }
            swap(nums, i, j)
        }
        reverse(nums, i + 1)
        return nums
    }

    private fun reverse(nums: IntArray, start: Int) {
        var i = start
        var j = nums.size - 1
        while (i < j) {
            swap(nums, i, j)
            i++
            j--
        }
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }

    @Test
    fun runTests() {
        assertTrue(intArrayOf(1, 3, 2).contentEquals(nextPermutation(intArrayOf(1, 2, 3))))
        assertTrue(intArrayOf(1, 2, 3).contentEquals(nextPermutation(intArrayOf(3, 2, 1))))
        assertTrue(intArrayOf(2, 1, 3).contentEquals(nextPermutation(intArrayOf(1, 3, 2))))
    }

}