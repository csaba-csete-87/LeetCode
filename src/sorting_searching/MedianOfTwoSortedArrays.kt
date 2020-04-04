package sorting_searching

import org.junit.Test
import kotlin.test.assertEquals

class MedianOfTwoSortedArrays {

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val nums = IntArray(nums1.size + nums2.size)
        var i = 0
        var j = 0

        while (i < nums1.size && j < nums2.size) {
            val num1 = nums1[i]
            val num2 = nums2[j]
            if (num1 == num2) {
                nums[i + j] = num1
                nums[i + j + 1] = num2
                if (i < nums1.size) {
                    i++
                }
                if (j < nums2.size) {
                    j++
                }
            } else if (num1 < num2) {
                nums[i + j] = num1
                if (i < nums1.size) {
                    i++
                }
            } else {
                nums[i + j] = num2
                if (j < nums2.size) {
                    j++
                }
            }
        }
        while (i < nums1.size) {
            nums[i + j] = nums1[i]
            if (i < nums1.size) {
                i++
            }
        }
        while (j < nums2.size)     {
            nums[i + j] = nums2[j]
            if (j < nums2.size) {
                j++
            }
        }

        if (nums.size % 2 == 1) {
            val mid = nums[nums.size / 2]
            return mid.toDouble()
        } else {
            val mid1 = nums[nums.size / 2 - 1]
            val mid2 = nums[nums.size / 2]
            return ((mid1 + mid2).toFloat() / 2).toDouble()
        }
    }

    @Test
    fun runTests() {
        assertEquals(2.toDouble(), findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2)))
        assertEquals(2.5, findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)))
        assertEquals(0.toDouble(), findMedianSortedArrays(intArrayOf(0, 0), intArrayOf(0, 0)))
    }
}