package mocks

import org.junit.Test
import kotlin.math.max
import kotlin.test.assertEquals

class MinimumDominoRotations {

    fun minDominoRotations(A: IntArray, B: IntArray): Int {
        val minA = minRotationsForValue(A, B, A[0])
        return if (minA == -1) {
            minRotationsForValue(A, B, B[0])
        } else {
            minA
        }
    }

    fun minRotationsForValue(A: IntArray, B: IntArray, target: Int): Int {
        var top = 0
        var bot = 0
        for (i in A.indices) {
            val a = A[i]
            val b = B[i]
            if (a != target && b != target) {
                return -1
            }
            if (a == target && b == target) {
                continue
            }
            if (a == target) {
                bot++
            }
            if (b == target) {
                top++
            }
        }
        return Math.min(top, bot)
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