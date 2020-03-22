import org.junit.Test

class WordSearch2 {

    class TrieNode(
        val children: HashMap<Char, TrieNode> = hashMapOf(),
        var word: String? = null
    )

    private val root = TrieNode()
    private val results = ArrayList<String>()
    private lateinit var board: Array<CharArray>
    private val rowOffsets = intArrayOf(-1, 0, 1, 0)
    private val colOffsets = intArrayOf(0, 1, 0, -1)

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        this.board = board
        initDictionary(words)

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (root.children.containsKey(board[i][j])) {
                    backtracking(i, j, root)
                }
            }
        }
        return results
    }

    private fun backtracking(i: Int, j: Int, parent: TrieNode?) {
        val letter = board[i][j]
        val node = parent?.children?.get(letter)
        val word = node?.word
        if (word != null) {
            results.add(word)
            node.word = null
        }

        for (k in 0..3) {
            val n = i + rowOffsets[k]
            val m = j + colOffsets[k]
            if (n < 0 || n >= board.size || m < 0 || m >= board[0].size) {
                continue
            }
            if (node?.children?.containsKey(board[n][m]) == true) {
                backtracking(n, m, node)
            }
        }

        if (node?.children?.isEmpty() == true) {
            parent.children.remove(letter)
        }
    }

    private fun initDictionary(words: Array<String>) {
        words.forEach { word ->
            insert(word.toCharArray(), 0, root)
        }
    }

    private fun insert(chars: CharArray, depth: Int, node: TrieNode?) {
        if (node == null) return
        if (depth >= chars.size) return
        val c = chars[depth]
        if (node.children[c] == null) {
            node.children[c] = TrieNode()
        }
        if (depth == chars.size - 1) {
            node.children[c]?.word = String(chars)
        }
        insert(chars, depth + 1, node.children[c])
    }

    @Test
    fun runTests() {
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
    }
}