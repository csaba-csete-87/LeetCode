import org.junit.Test
import kotlin.math.ceil
import kotlin.test.assertEquals

class LicenceKeyFormatting {

    private fun licenseKeyFormatting(s: String, k: Int): String {
        val c = s.filterNot { it == "-"[0] }
        val firstGroupLength = c.length % k
        val nrOfGroups = ceil((c.length.toFloat() / k)).toInt()
        var result = ""
        if (firstGroupLength > 0) {
            result = result.plus(c.substring(0, firstGroupLength))
            if (nrOfGroups > 1) {
                result = result.plus("-")
            }
        }
        val until = if (firstGroupLength == 0) nrOfGroups else nrOfGroups - 1
        for (i in 0 until until) {
            val start = i * k + firstGroupLength
            result = result.plus(c.substring(start, start + k))
            if (i < until - 1) {
                result = result.plus("-")
            }
        }
        return result.toUpperCase()
    }

    private fun licenseKeyFormattingV2(s: String, k: Int): String {
        var result = StringBuilder()
        for (i in s.length - 1 downTo 0)
            if (s[i] != '-') {
                result = result.append(if (result.length % (k + 1) == k) "-" else "").append(s[i])
            }
        return result.toString().reversed().toUpperCase()
    }

    @Test
    fun runTest() {
        assertEquals("5F3Z-2E9W", licenseKeyFormattingV2("5F3Z-2e-9-w", 4))
        assertEquals("2-5G-3J", licenseKeyFormattingV2("2-5g-3-J", 2))
        assertEquals("2", licenseKeyFormattingV2("2", 2))
        assertEquals(
            "K-MHVVUPIYOBPJTHZMDHZVBWQNWFDAJFIWUQVSUFRQSTUHORFISEJIBHTNEPRLBHJFNDNWFIJCTWBSKLJKRATHQSOWBOGDNAQODJO",
            licenseKeyFormattingV2(
                "----------kmhvVuPIyobPjThzMdhzvBWqNwfDajFiWUQvSUfrQsTuHorFisEjIbHtNEPrLbHJFnDNWFijctwBskljKratHqSOWBOgDnaQodjo",
                99
            )
        )
    }
}