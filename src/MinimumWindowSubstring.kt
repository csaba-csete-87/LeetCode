import org.junit.Test

class MinimumWindowSubstring {
    fun minWindow(s: String, t: String): String {
        if (s.isEmpty() || t.isEmpty()) {
            return ""
        }
        val targets = hashMapOf<Char, Int>()
        for (tc in t) {
            val count = targets[tc] ?: 0
            targets[tc] = count + 1
        }
        var l = 0
        var r = t.length

        var result = ""
        while (r <= s.length && r - l >= t.length) {
            val crt = s.substring(l, r)
            if (containsAllTargets(crt, targets)) {
                if (result.isEmpty()) {
                    result = crt
                } else {
                    if (crt.length < result.length) {
                        result = crt
                    }
                }
                l++
            } else {
                r++
            }
        }
        return result
    }

    private fun containsAllTargets(crt: String, targets: Map<Char, Int>): Boolean {
        targets.forEach { (k, v) ->
            if (crt.count { it == k } < v) {
                return false
            }
        }
        return true
    }

    @Test
    fun runTests() {
        println(minWindow("ADOBECODEBANC", "ABC")) //BANC
        println(minWindow("bbaa", "aba")) //baa
    }

}