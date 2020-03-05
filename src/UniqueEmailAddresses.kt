import org.junit.Test
import kotlin.test.assertEquals

class UniqueEmailAddresses {

    fun numUniqueEmails(emails: Array<String>): Int {
        val uniqueEmails: MutableSet<String> = mutableSetOf()
        for (email in emails) {
            val trimmedEmail = getTrimmedEmail(email)
            if (!uniqueEmails.contains(trimmedEmail)) {
                uniqueEmails.add(trimmedEmail)
            }
        }
        return uniqueEmails.size
    }

    private fun getTrimmedEmail(email: String): String {
        val dot = ".".single()
        val at = "@".single()
        val plus = "+".single()
        val emailArray = email.toCharArray()
        val builder = StringBuilder()
        var hasReachedPlus = false
        var hasReachedAt = false


        for (c in emailArray) {
            if (c == at) {
                hasReachedAt = true
            }
            if (!hasReachedAt) {
                if (c == dot) {
                    continue
                }
                if (c == plus) {
                    hasReachedPlus = true
                }
                if (hasReachedPlus) {
                    continue
                }
            }
            builder.append(c)
        }
        return builder.toString()
    }

    @Test
    fun testEmailAddresses() {
        assertEquals(
            2,
            numUniqueEmails(
                arrayOf(
                    "test.email+alex@leetcode.com",
                    "test.e.mail+bob.cathy@leetcode.com",
                    "testemail+david@lee.tcode.com"
                )
            )
        )
    }
}