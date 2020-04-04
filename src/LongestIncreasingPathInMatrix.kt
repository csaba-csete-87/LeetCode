import org.junit.Test
import java.util.*

class LongestIncreasingPathInMatrix {
    val directions = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1)
    )

    lateinit var cache: Array<IntArray>
    var rows = 0
    var cols = 0

    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        if (matrix.isEmpty()) return 0
        rows = matrix.size
        cols = matrix[0].size
        cache = Array(rows) { IntArray(cols) }
        var maxPath = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                maxPath = Math.max(maxPath, dfs(i, j, matrix))
            }
        }
        return maxPath
    }

    fun dfs(i: Int, j: Int, matrix: Array<IntArray>): Int {
        if (cache[i][j] > 0) return cache[i][j]
        for (d in directions) {
            val x = i + d[0]
            val y = j + d[1]
            if (x in 0 until rows && y in 0 until cols && matrix[x][y] > matrix[i][j]) {
                val next = dfs(x, y, matrix)
                cache[i][j] = Math.max(cache[i][j], next)
            }
        }
        return ++cache[i][j]
    }

    fun backtracking(i: Int, j: Int, matrix: Array<IntArray>): Int {
        val q = LinkedList<Triple<Int, Int, Int>>()
        val p = Triple(i, j, 1)
        q.addLast(p)
        var maxLength = 1
        while (q.isNotEmpty()) {
            val n = q.pop()
            val v = matrix[n.first][n.second]
            maxLength = Math.max(n.third, maxLength)
            if (n.first > 0) {
                val top = matrix[n.first - 1][n.second]
                if (top > v) {
                    q.addLast(Triple(n.first - 1, n.second, n.third + 1))
                }
            }
            if (n.second < matrix[n.first].size - 1) {
                val right = matrix[n.first][n.second + 1]
                if (right > v) {
                    q.addLast(Triple(n.first, n.second + 1, n.third + 1))
                }
            }
            if (n.first < matrix.size - 1) {
                val bot = matrix[n.first + 1][n.second]
                if (bot > v) {
                    q.addLast(Triple(n.first + 1, n.second, n.third + 1))
                }
            }
            if (n.second > 0) {
                val left = matrix[n.first][n.second - 1]
                if (left > v) {
                    q.addLast(Triple(n.first, n.second - 1, n.third + 1))
                }
            }
        }
        return maxLength
    }

    @Test
    fun runTests() {
        println(longestIncreasingPath(arrayOf(
                intArrayOf(9, 9, 4),
                intArrayOf(6, 6, 8),
                intArrayOf(2, 1, 1)
        )))
        println(longestIncreasingPath(arrayOf(
                intArrayOf(3, 4, 5),
                intArrayOf(3, 2, 6),
                intArrayOf(2, 2, 1)
        )))
        println(longestIncreasingPath(arrayOf(
                intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
                intArrayOf(19, 18, 17, 16, 15, 14, 13, 12, 11, 10),
                intArrayOf(20, 21, 22, 23, 24, 25, 26, 27, 28, 29),
                intArrayOf(39, 38, 37, 36, 35, 34, 33, 32, 31, 30),
                intArrayOf(40, 41, 42, 43, 44, 45, 46, 47, 48, 49),
                intArrayOf(59, 58, 57, 56, 55, 54, 53, 52, 51, 50),
                intArrayOf(60, 61, 62, 63, 64, 65, 66, 67, 68, 69),
                intArrayOf(79, 78, 77, 76, 75, 74, 73, 72, 71, 70),
                intArrayOf(80, 81, 82, 83, 84, 85, 86, 87, 88, 89),
                intArrayOf(99, 98, 97, 96, 95, 94, 93, 92, 91, 90),
                intArrayOf(100, 101, 102, 103, 104, 105, 106, 107, 108, 109),
                intArrayOf(119, 118, 117, 116, 115, 114, 113, 112, 111, 110),
                intArrayOf(120, 121, 122, 123, 124, 125, 126, 127, 128, 129),
                intArrayOf(139, 138, 137, 136, 135, 134, 133, 132, 131, 130),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        )))
    }
}