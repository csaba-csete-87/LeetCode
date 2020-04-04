package sorting_searching

import org.junit.Test
import java.util.*
import kotlin.math.max

class MergeIntervals {

    private class IntervalComparator : Comparator<IntArray> {

        override fun compare(a: IntArray, b: IntArray): Int {
            return if (a[0] < b[0]) -1 else if (a[0] == b[0]) 0 else 1
        }
    }

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val sorted = intervals.asList().sortedWith(IntervalComparator())

        val merged = LinkedList<IntArray>()
        for (i in sorted) {
            if (merged.isEmpty() || merged.last[1] < i[0]) {
                merged.add(i)
            } else {
                merged.last[1] = max(merged.last[1], i[1])
            }
        }
        return merged.toTypedArray()
    }

    @Test
    fun runTests() {
        val result = merge(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(2, 6),
                intArrayOf(8, 10),
                intArrayOf(15, 18)
            )
        )
        for(i in result) {
            println("${i[0]}:${i[1]}")
        }
    }
}