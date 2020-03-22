import org.junit.Test

class WordSquares {

    private var length = 0
    private lateinit var words: Array<String>

    fun wordSquares(input: Array<String>): List<List<String>> {
        length = input[0].length
        words = input

        val results = arrayListOf<ArrayList<String>>()

        for (word in words) {
            val wordSquares = arrayListOf<String>()
            wordSquares.add(word)

            backtracking(1, wordSquares, results)
        }
        return results
    }

    private fun backtracking(step: Int, wordSquares: ArrayList<String>, results: ArrayList<ArrayList<String>>) {
        if (step == length) {
            results.add(wordSquares.clone() as ArrayList<String>)
            return
        }

        val prefixBuilder = StringBuilder()
        for (word in wordSquares) {
            prefixBuilder.append(word[step])
        }
        val candidates = getWordsByPrefix(prefixBuilder.toString())
        for (candidate in candidates) {
            wordSquares.add(candidate)
            backtracking(step + 1, wordSquares, results)
            wordSquares.removeAt(wordSquares.size - 1)
        }
    }

    fun getWordsByPrefix(prefix: String): List<String> {
        val wordsByPrefix = arrayListOf<String>()
        for (word in words) {
            if (word.startsWith(prefix)) {
                wordsByPrefix.add(word)
            }
        }
        return wordsByPrefix
    }

    @Test
    fun runTests() {
        val result1 = wordSquares(arrayOf("area", "lead", "wall", "lady", "ball"))
        println(result1)
        val result2 = wordSquares(arrayOf("abat", "baba", "atan", "atal"))
        println(result2)
    }
}