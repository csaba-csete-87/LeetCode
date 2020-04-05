package recursion

class LetterCombinations {

    val charMap = hashMapOf<Char, Array<String>>()

    init {
        charMap['2'] = arrayOf("a", "b", "c")
        charMap['3'] = arrayOf("d", "e", "f")
        charMap['4'] = arrayOf("g", "h", "i")
        charMap['5'] = arrayOf("j", "k", "l")
        charMap['6'] = arrayOf("m", "n", "o")
        charMap['7'] = arrayOf("p", "q", "r", "s")
        charMap['8'] = arrayOf("t", "u", "v")
        charMap['9'] = arrayOf("w", "x", "y", "z")
    }

    fun letterCombinations(digits: String): List<String> {
        var i = digits.length - 2
        var results = arrayListOf<String>()
        val lastLetters: Array<String> = charMap[digits[digits.length - 1]] ?: arrayOf()
        results.addAll(lastLetters)

        while (i >= 0) {
            val combos = charMap[digits[i]] ?: arrayOf()
            val newResults = arrayListOf<String>()
            combos.forEach { c ->
                for (r in results) {
                    newResults.add("$c$r")
                }
            }
            results = newResults
            i--
        }
        return results
    }
}