package arrays_strings

import org.junit.Test
import kotlin.test.assertEquals

class MeetingRooms2 {

    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        val times = Array(intervals.size * 2) { Pair(0, true) }
        for (i in intervals.indices) {
            times[i] = Pair(intervals[i][0], true)
        }
        for (i in intervals.indices) {
            times[i + intervals.size] = Pair(intervals[i][1], false)
        }
        times.sortWith(PairComparator())
        var maxCnt = 0
        var cnt = 0
        for (item in times) {
            if (item.second) {
                cnt++
                maxCnt = Math.max(cnt, maxCnt)
            } else {
                cnt--
            }
        }
        return maxCnt
    }

    private class PairComparator : Comparator<Pair<Int, Boolean>> {

        override fun compare(a: Pair<Int, Boolean>, b: Pair<Int, Boolean>): Int {
            return if (a.first == b.first) {
                return when {
                    a.second == b.second -> 0
                    a.second -> 1
                    else -> -1
                }
            } else {
                a.first.compareTo(b.first)
            }
        }
    }

    @Test
    fun runTests() {
        assertEquals(
            1, minMeetingRooms(
                arrayOf(
                    intArrayOf(13, 15),
                    intArrayOf(1, 13)
                )
            )
        )
        assertEquals(
            2, minMeetingRooms(
                arrayOf(
                    intArrayOf(0, 30),
                    intArrayOf(5, 10),
                    intArrayOf(15, 20)
                )
            )
        )
        assertEquals(
            1, minMeetingRooms(
                arrayOf(
                    intArrayOf(7, 10),
                    intArrayOf(2, 4)
                )
            )
        )
    }
}