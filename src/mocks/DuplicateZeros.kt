package mocks

import org.junit.Test

class DuplicateZeros {

    fun duplicateZeros(arr: IntArray) {
        var i = 0
        while (i < arr.size) {
            val n = arr[i]
            if (n == 0 && i < arr.size - 1) {
                for (j in arr.size - 2 downTo i) {
                    arr[j + 1] = arr[j]
                }
                i += 2
            } else {
                i++
            }
        }
    }

    @Test
    fun runTests() {
        val arr = intArrayOf(1, 0, 2, 3, 0, 4, 5, 0)
        duplicateZeros(arr)
        println(arr)
    }
}