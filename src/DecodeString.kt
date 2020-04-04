import org.junit.Test

class DecodeString {
    val zero = '0'
    val nine = '9'

    fun decodeString(s: String): String {
        var i = 0
        var nr = ""
        val sb = StringBuilder()
        var isContentMode = false
        var content = ""
        var opened = 0
        while (i < s.length) {
            val c = s[i]
            if(!contentMode && c in zero..nine) {
                nr.plus(c)
            } else if (c == '[') {
                if(!contentMode) {
                    isContentMode = true
                }
                opened++
            } else if (c == ']') {
                opened--
                if(opened == 0){
                    val m = nr.toInt()
                    for(j in 1..m){
                        sb.append(decodeString(content))
                    }
                    nr = ""
                    isContentMode = false
                    content = ""
                }
            } else {
                if(isContentMode){
                    content = content.plus(c)
                } else {
                    sb.append(c)
                }
            }
            i++
        }
    }

    @Test
    fun runTests() {

    }
}