import org.junit.Test

class InsertInterval {

    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) {
            return arrayOf(newInterval)
        }
        val result: ArrayList<IntArray> = arrayListOf()
        var left = newInterval[0]
        var right = newInterval[1]
        var mergedAdded = false
        for (crtInt in intervals) {
            if (crtInt[1] < newInterval[0]) {
                result.add(crtInt)
                continue
            } else if (crtInt[0] > newInterval[1]) {
                if (!mergedAdded) {
                    mergedAdded = true
                    result.add(intArrayOf(left, right))
                }
                result.add(crtInt)
                continue
            } else {
                left = Math.min(left, crtInt[0])
                right = Math.max(right, crtInt[1])
            }
        }
        if (!mergedAdded) {
            result.add(intArrayOf(left, right))
        }


        return result.toTypedArray()
    }

    @Test
    fun runTest() {
        val r2 = insert(
                arrayOf(
                        intArrayOf(1, 2),
                        intArrayOf(3, 5),
                        intArrayOf(6, 7),
                        intArrayOf(8, 10),
                        intArrayOf(12, 16)
                ),
                intArrayOf(4, 8)
        )
        val r1 = insert(
                arrayOf(
                        intArrayOf(1, 5)
                ),
                intArrayOf(2, 3)
        )
    }
}