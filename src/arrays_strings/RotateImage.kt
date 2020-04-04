package arrays_strings

import org.junit.Test

class RotateImage {

    fun rotate(matrix: Array<IntArray>) {
        if (matrix.size == 1) return

        for (i in 0 until matrix.size - 1) {
            for (j in i until matrix[i].size - 1 - i) {
                val tmp = matrix[i][j]
                matrix[i][j] = matrix[matrix.size - 1 - j][i]
                matrix[matrix.size - 1 - j][i] = matrix[matrix.size - 1 - i][matrix[i].size - 1 - j]
                matrix[matrix.size - 1 - i][matrix[i].size - 1 - j] = matrix[j][matrix[i].size - 1 - i]
                matrix[j][matrix[i].size - 1 - i] = tmp
            }
        }
    }

    @Test
    fun runTests() {
        val m1 = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
        )
        rotate(m1)
        print(m1)
        val m2 = arrayOf(
                intArrayOf(5, 1, 9, 11),
                intArrayOf(2, 4, 8, 10),
                intArrayOf(13, 3, 6, 7),
                intArrayOf(15, 14, 12, 16)
        )
        rotate(m2)
        print(m2)
    }
}