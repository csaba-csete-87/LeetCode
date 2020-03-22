import org.junit.Test

class StroboGrammaticNumberII {

    val stroboMap: HashMap<Int, List<String>> = hashMapOf()
    val zero = "0"[0].toInt()

    fun findStrobogrammatic(n: Int): List<String> {
        if(n < 0) return listOf("")
        val nrs = findAllStroboNums(n)
        return nrs.filter { it[0].toInt() > zero }
    }

    private fun findAllStroboNums(n: Int): List<String> = when(n) {
        0 -> listOf("")
        1 -> if(stroboMap[1] != null){
            stroboMap[1]!!
        } else {
            stroboMap[1] = listOf("0","1","8")
            stroboMap[1]!!
        }
        2 -> if(stroboMap[2] != null){
            stroboMap[2]!!
        } else {
            stroboMap[2] = listOf("00", "11","69","88", "96")
            stroboMap[2]!!
        }
        else -> if(stroboMap[n] != null){
            stroboMap[n]!!
        } else {
            val nrs = arrayListOf<String>()
            val outs = findAllStroboNums(2)
            val ins = findAllStroboNums(n-2)
            outs.forEach { out ->
                ins.forEach {
                    nrs.add("${out[0]}$it${out[1]}")
                }
            }
            stroboMap[n] = nrs
            stroboMap[n]!!
        }
    }

    @Test
    fun runTests() {
        findStrobogrammatic(3)
    }
    //n > 0
    //n = 1: 0,1,8
    //n = 2: 00,11,69,88,96
    //n = 3: 000, 010, 080, 101,111,181,609,619,689...
    //n = 4: 0000, 0110, 0690, 0880, 0960, 1001,1111,1691,1881,1961
    //n = 5: 10001, 10101, 10801
}