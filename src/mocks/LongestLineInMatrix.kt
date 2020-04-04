package mocks

import org.junit.Test

class LongestLineInMatrix {

    var rows = 0
    var cols = 0

    private val dirs = arrayOf(
        arrayOf(
            Pair(-1, -1),
            Pair(1, 1)
        ),
        arrayOf(
            Pair(-1, 0),
            Pair(1, 0)
        ),
        arrayOf(
            Pair(-1, 1),
            Pair(1, -1)
        ),
        arrayOf(
            Pair(0, -1),
            Pair(0, 1)
        )
    )

    fun longestLine(M: Array<IntArray>): Int {
        rows = M.size
        cols = M[0].size

        if (rows == 0 || cols == 0) {
            return 0
        }

        var longestLine = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val crtLine = longestLineOfNode(i, j, M)
                longestLine = Math.max(longestLine, crtLine)
            }
        }
        return longestLine
    }

    private fun longestLineOfNode(i: Int, j: Int, M: Array<IntArray>): Int {
        val n = M[i][j]
        if (n == 0) return 0

        var maxLength = 0
        for (dir in dirs) {
            var length = 1
            val d1 = dir[0]
            var a = i + d1.first
            var b = j + d1.second
            while (a in 0 until rows && b in 0 until cols) {
                val n1 = M[a][b]
                if (n1 == 1) {
                    length++
                    a += d1.first
                    b += d1.second
                } else {
                    break
                }
            }
            val d2 = dir[1]
            var c = i + d2.first
            var d = j + d2.second
            while (c in 0 until rows && d in 0 until cols) {
                val n2 = M[c][d]
                if (n2 == 1) {
                    length++
                    c += d2.first
                    d += d2.second
                } else {
                    break
                }
            }
            maxLength = Math.max(length, maxLength)
        }
        return maxLength
    }

    @Test
    fun runTest() {
        println(
            longestLine(
                arrayOf(
                    intArrayOf(0, 1, 1, 0),
                    intArrayOf(0, 1, 1, 0),
                    intArrayOf(0, 0, 0, 1)
                )
            )
        )
    }
}