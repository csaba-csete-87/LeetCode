import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class JumpGame {
    fun canJump(nums: IntArray): Boolean {
        if (nums.size == 1) {
            return true
        }
        val cache = BooleanArray(nums.size)
        dfs(0, nums, cache)
        return cache.any { it }
    }

    fun dfs(index: Int, nums: IntArray, cache: BooleanArray) {
        val n = nums[index]
        if (n == 0) {
            cache[index] = false
        }
        for (i in 1..n) {
            if (index + i >= nums.size - 1) {
                cache[index] = true
                break
            }
            dfs(index + i, nums, cache)
        }
    }

    @Test
    fun runTests() {
        assertTrue(canJump(intArrayOf(8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5)))
        assertTrue(canJump(intArrayOf(0)))
        assertTrue(canJump(intArrayOf(1, 2, 0, 1)))
        assertTrue(canJump(intArrayOf(2, 3, 1, 1, 4)))
        assertFalse(canJump(intArrayOf(3, 2, 1, 0, 4)))
    }
}