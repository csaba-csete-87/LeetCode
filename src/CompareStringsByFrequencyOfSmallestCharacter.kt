import org.junit.Assert.assertTrue
import org.junit.Test

class CompareStringsByFrequencyOfSmallestCharacter {

    private val letterCount = 26
    private val a = "a".toCharArray()[0].toInt()

    private fun numSmallerByFrequency(queries: Array<String>, words: Array<String>): IntArray {
        val result = IntArray(queries.size)

        val qFrequencies = getMinFrequencyArray(queries)
        val wFrequencies = getMinFrequencyArray(words)

        for (i in qFrequencies.indices) {
            val qf = qFrequencies[i]
            for (j in wFrequencies.indices) {
                val wf = wFrequencies[j]
                if (qf < wf) {
                    result[i]++
                }
            }
        }

        return result
    }

    private fun getMinFrequencyArray(input: Array<String>): IntArray {
        val frequencyArray = IntArray(input.size)
        for (i in input.indices) {
            val map = IntArray(letterCount)
            val query = input[i]
            var minChar = letterCount
            for (queryChar: Char in query) {
                val charIntVal = queryChar.toInt() - a
                if (charIntVal < minChar) {
                    minChar = charIntVal
                }
                map[charIntVal]++
            }
            frequencyArray[i] = map[minChar]
        }
        return frequencyArray
    }

    @Test
    fun runTest() {
        val expected1 = intArrayOf(1)
        val result1 = numSmallerByFrequency(arrayOf("cbd"), arrayOf("zaaaz"))
        assertTrue(expected1.contentEquals(result1))

        val expected2 = intArrayOf(1, 2)
        val result2 = numSmallerByFrequency(arrayOf("bbb", "cc"), arrayOf("a", "aa", "aaa", "aaaa"))
        assertTrue(expected2.contentEquals(result2))
    }
}