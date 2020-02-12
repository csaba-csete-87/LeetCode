import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoggerRateLimiter {

    class Logger() {

        private val lastPrintedAt = HashMap<String, Int>()
        private val timeLimit = 10

        fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
            val hasPrintedMessage = lastPrintedAt.contains(message)
            return if (!hasPrintedMessage) {
                lastPrintedAt[message] = timestamp
                true
            } else {
                val lastPrintOfMessage = lastPrintedAt[message] ?: 0
                if (timestamp - timeLimit >= lastPrintOfMessage) {
                    lastPrintedAt[message] = timestamp
                    true
                } else {
                    false
                }
            }
        }

    }

    @Test
    fun runTest() {
        val logger = Logger()

        // logging string "foo" at timestamp 1
        assertTrue(logger.shouldPrintMessage(1, "foo"))

        // logging string "bar" at timestamp 2
        assertTrue(logger.shouldPrintMessage(2, "bar"))

        // logging string "foo" at timestamp 3
        assertFalse(logger.shouldPrintMessage(3, "foo"))

        // logging string "bar" at timestamp 8
        assertFalse(logger.shouldPrintMessage(8, "bar"))

        // logging string "foo" at timestamp 10
        assertFalse(logger.shouldPrintMessage(10, "foo"))

        // logging string "foo" at timestamp 11
        assertTrue(logger.shouldPrintMessage(11, "foo"))
    }
}