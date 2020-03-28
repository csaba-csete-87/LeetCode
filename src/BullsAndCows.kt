import org.junit.Test
import kotlin.math.min
import kotlin.test.assertEquals

class BullsAndCows {

    private fun getHint(secret: String, guess: String): String {
        val secretF = IntArray(10)
        val guessF = IntArray(10)
        val zero = "0"[0].toInt()

        var bulls = 0
        for (i in secret.indices) {
            val s = secret[i].toInt() - zero
            secretF[s]++
            val g = guess[i].toInt() - zero
            guessF[g]++
            if (s == g) {
                bulls++
            }
        }

        var cows = 0
        for (j in 0 until 10) {
            cows += min(secretF[j], guessF[j])
        }
        return bulls.toString().plus("A").plus(cows - bulls).plus("B")
    }

    @Test
    fun runTest() {
        assertEquals(
            "7A30B",
            getHint("102937461209471239587103456139045610345761", "109345613409756103470579613409576130495613")
        )
        assertEquals("1A3B", getHint("1807", "7810"))
        assertEquals("1A1B", getHint("1123", "0111"))
    }
}