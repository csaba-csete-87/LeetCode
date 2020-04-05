package arrays_strings

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class AddBoldTag {

    private class IntervalComparator : Comparator<IntArray> {

        override fun compare(i1: IntArray, i2: IntArray): Int {
            return i1[0] - i2[0]
        }
    }

    fun addBoldTag(s: String, dict: Array<String>): String {
        val intervals = arrayListOf<IntArray>()

        for (word in dict) {
            var startIndex = 0
            while (startIndex < s.length && startIndex >= 0) {
                val indexOfWord = s.indexOf(word, startIndex)
                if (indexOfWord >= 0) {
                    intervals.add(intArrayOf(indexOfWord, indexOfWord + word.length - 1))
                    startIndex = indexOfWord + 1
                } else {
                    break
                }
            }
        }



        //merge intervals
        intervals.sortWith(IntervalComparator())
        val merged = LinkedList<IntArray>()
        for (i in intervals) {
            if (merged.isEmpty() || merged.last[1] < i[0]) {
                merged.add(i)
            } else {
                merged.last[1] = Math.max(merged.last[1], i[1])
            }
        }
        val startTags = arrayListOf<Int>()
        val endTags = arrayListOf<Int>()
        for (m in merged) {
            startTags.add(m[0])
            endTags.add(m[1])
        }

        val sb = StringBuilder()
        for (i in s.indices) {
            if (startTags.contains(i)) {
                sb.append("<b>")
            }
            sb.append(s[i])
            if (endTags.contains(i)) {
                sb.append("</b>")
            }
        }
        var result = sb.toString()
        result = result.replace("</b><b>", "")
        return result
    }

    @Test
    fun runTests() {
        assertEquals(
            "<b>qrzjsorbkmyzzzvoqxefvxkcwtpk</b>hzbakuufbpgdky<b>km</b>ojwuennrjeciqvvacpzrrczfhxnsmginzwinzihpomxtmwey<b>yzz</b>mgcoiupjnidphvzlnxtcogufozlenjf<b>vo</b>kztghwckzyvmktduqkizixzxpanjwrdeudjyftxksjgdklwxrhmudhrtemuvelykqaafzlqmennttkighcdxfozdcoqkyshhajipnsdrljrnlwmyjuwxsebpqm",
            addBoldTag(
                "qrzjsorbkmyzzzvoqxefvxkcwtpkhzbakuufbpgdkykmojwuennrjeciqvvacpzrrczfhxnsmginzwinzihpomxtmweyyzzmgcoiupjnidphvzlnxtcogufozlenjfvokztghwckzyvmktduqkizixzxpanjwrdeudjyftxksjgdklwxrhmudhrtemuvelykqaafzlqmennttkighcdxfozdcoqkyshhajipnsdrljrnlwmyjuwxsebpqm",
                arrayOf("qr", "zj", "so", "rb", "km", "yz", "zz", "vo", "qx", "ef", "vx", "kc", "wt", "pk")
            )
        )
        assertEquals(
            "<b>aaabbcc</b>",
            addBoldTag(
                "aaabbcc", arrayOf(
                    "aaa",
                    "aab",
                    "bc",
                    "aaabbcc"
                )
            )
        )
        assertEquals(
            "<b>aaabbc</b>c",
            addBoldTag(
                "aaabbcc", arrayOf(
                    "aaa",
                    "aab",
                    "bc"
                )
            )
        )
    }

}