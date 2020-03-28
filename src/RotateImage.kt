import org.junit.Test

class RotateImage {

    fun rotate(matrix: Array<IntArray>) {
        val offset = if (matrix.size % 2 == 1) 1 else 0
        val size = matrix.size / 2 + offset
        for (j in 0 until size) {
            for (i in j until size) {
                val topLeft = matrix[i][j]
                val topRight = matrix[i][matrix[i].size - 1 - j]
                val botLeft = matrix[matrix.size - 1 - i][j]
                val botRight = matrix[matrix.size - 1 - i][matrix[i].size - 1 - j]
                val tmp = topLeft
                matrix[i][j] = botLeft
                matrix[matrix.size - 1 - i][j] = botRight
                matrix[matrix.size - 1 - i][matrix[i].size - 1 - j] = topRight
                matrix[i][matrix[i].size - 1 - j] = tmp
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