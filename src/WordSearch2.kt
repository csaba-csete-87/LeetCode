import org.junit.Test
import java.util.*

class WordSearch2 {
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val startingChars = hashMapOf<Char, ArrayList<String>>()
        words.forEach {
            if (startingChars[it[0]] == null) {
                startingChars[it[0]] = arrayListOf(it)
            } else {
                startingChars[it[0]]?.add(it)
            }
        }
        val coordsOfStartingChars = hashMapOf<Char, ArrayList<Pair<Int, Int>>>()
        for (i in 0 until board.size) {
            val boardRow = board[i]
            for (j in 0 until boardRow.size) {
                val c = boardRow[j]
                if (startingChars.contains(c)) {
                    if (coordsOfStartingChars[c] == null) {
                        coordsOfStartingChars[c] = arrayListOf(Pair(i, j))
                    } else {
                        coordsOfStartingChars[c]?.add(Pair(i, j))
                    }
                }
            }
        }
        val results = arrayListOf<String>()
        for (startingChar in coordsOfStartingChars.keys) {
            for (word in startingChars[startingChar] ?: arrayListOf()) {
                for (coord in coordsOfStartingChars[startingChar]!!) {
                    isWordOnBoard(board, word, coord.first, coord.second, results)
                }
            }
        }
        return results
    }

    private fun isWordOnBoard(
            board: Array<CharArray>,
            word: String,
            i: Int,
            j: Int,
            results: ArrayList<String>
    ) {
        val stack = Stack<Triple<Int, Int, Int>>()
        val visited = mutableSetOf<String>()
        stack.push(Triple(0, i, j))
        visited.add("${word[0]},$i:$j")
        while (!stack.isEmpty()) {
            val element = stack.pop()
            if (element.first == word.length - 1) {
                if(!results.contains(word)){
                    results.add(word)
                }
                break
            }
            var addedElementInRound = false
            if (element.third > 0) {
                val left = board[element.second][element.third - 1]
                if (left == word[element.first + 1]) {
                    val leftId = "${word[element.first+1]},${element.second}:${element.third - 1}"
                    if (!visited.contains(leftId)) {
                        addedElementInRound = true
                        stack.push(Triple(element.first + 1, element.second, element.third - 1))
                    }
                }
            }
            if (element.second > 0) {
                val top = board[element.second - 1][element.third]
                if (top == word[element.first + 1]) {
                    val topId = "${word[element.first+1]},${element.second - 1}:${element.third}"
                    if (!visited.contains(topId)) {
                        addedElementInRound = true
                        stack.push(Triple(element.first + 1, element.second - 1, element.third))
                    }
                }
            }
            if (element.third < board[0].size - 1) {
                val right = board[element.second][element.third + 1]
                if (right == word[element.first + 1]) {
                    val rightId = "${word[element.first+1]},${element.second}:${element.third + 1}"
                    if (!visited.contains(rightId)) {
                        addedElementInRound = true
                        stack.push(Triple(element.first + 1, element.second, element.third + 1))
                    }
                }
            }
            if (element.second < board.size - 1) {
                val bot = board[element.second + 1][element.third]
                if (bot == word[element.first + 1]) {
                    val botId = "${word[element.first+1]},${element.second + 1}:${element.third}"
                    if (!visited.contains(botId)) {
                        addedElementInRound = true
                        stack.push(Triple(element.first + 1, element.second + 1, element.third))
                    }
                }
            }
            val elementId = "${word[element.first]},${element.second}:${element.third}"
            if(addedElementInRound) {
                visited.add(elementId)
            }
        }
    }

    @Test
    fun runTest() {
        println(
                findWords(
                        arrayOf(
                                "abc".toCharArray(),
                                "aed".toCharArray(),
                                "afg".toCharArray()
                        ),
                        arrayOf("abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade")
                )
        )
        println(
                findWords(
                        arrayOf(
                                "ab".toCharArray(),
                                "aa".toCharArray()
                        ),
                        arrayOf("aaba","aba","baa","bab","aaab","aaa","aaaa")
                )
        )
        println(
                findWords(
                        arrayOf(
                                "ab".toCharArray()
                        ),
                        arrayOf("aba")
                )
        )
        println(
                findWords(
                        arrayOf(
                                "aa".toCharArray()
                        ),
                        arrayOf("aaa")
                )
        )
        println(
                findWords(
                        arrayOf(
                                "oaan".toCharArray(),
                                "etae".toCharArray(),
                                "ihkr".toCharArray(),
                                "iflv".toCharArray()
                        ),
                        arrayOf("oath", "pea", "eat", "rain")
                )
        )
        println(
                findWords(
                        arrayOf(
                                "aa".toCharArray()
                        ),
                        arrayOf("aa")
                )
        )
    }
}