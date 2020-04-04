package trees_graphs

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class NumberOfIslands {

    val discoveredLandIds = mutableSetOf<String>()
    var discoveredIslands = 0
    val land = "1"[0]

    fun numIslands(grid: Array<CharArray>): Int {
        discoveredIslands = 0
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, item ->
                if (item == land) {
                    val landId = "$i:$j"
                    if (!discoveredLandIds.contains(landId)) {
                        expandLandDiscovery(i, j, grid)
                    }
                }
            }
        }
        return discoveredIslands
    }

    fun expandLandDiscovery(i: Int, j: Int, grid: Array<CharArray>) {
        val stack = Stack<Pair<Int, Int>>()
        stack.push(Pair(i, j))
        while (!stack.isEmpty()) {
            val currentLand = stack.pop()
            val landId = "${currentLand.first}:${currentLand.second}"
            if (!discoveredLandIds.contains(landId)) {
                discoveredLandIds.add(landId)
            }
            if (currentLand.second > 0) {
                val left = grid[currentLand.first][currentLand.second - 1]
                val leftId = "${currentLand.first}:${currentLand.second - 1}"
                if (left == land && !discoveredLandIds.contains(leftId)) {
                    stack.push(Pair(currentLand.first, currentLand.second - 1))
                }
            }
            if (currentLand.first > 0) {
                val top = grid[currentLand.first - 1][currentLand.second]
                val topId = "${currentLand.first - 1}:${currentLand.second}"
                if (top == land && !discoveredLandIds.contains(topId)) {
                    stack.push(Pair(currentLand.first - 1, currentLand.second))
                }
            }
            if (currentLand.second < grid[0].size-1) {
                val right = grid[currentLand.first][currentLand.second + 1]
                val rightId = "${currentLand.first}:${currentLand.second + 1}"
                if (right == land && !discoveredLandIds.contains(rightId)) {
                    stack.push(Pair(currentLand.first, currentLand.second + 1))
                }
            }
            if (currentLand.first < grid.size-1) {
                val bot = grid[currentLand.first + 1][currentLand.second]
                val botId = "${currentLand.first + 1}:${currentLand.second}"
                if (bot == land && !discoveredLandIds.contains(botId)) {
                    stack.push(Pair(currentLand.first + 1, currentLand.second))
                }
            }
        }
        discoveredIslands++
    }

    @Test
    fun runTest() {
        assertEquals(3, numIslands(arrayOf(
                "11000".toCharArray(),
                "11000".toCharArray(),
                "00100".toCharArray(),
                "00011".toCharArray()
        )))
        assertEquals(2, numIslands(arrayOf(
                "11110".toCharArray(),
                "11010".toCharArray(),
                "11000".toCharArray(),
                "00011".toCharArray()
        )))
        assertEquals(1, numIslands(arrayOf("1".toCharArray())))
    }
}