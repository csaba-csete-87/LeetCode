import org.junit.Test

class Contest182 {

    fun findLucky(arr: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        arr.forEach {
            val crtFreq = map[it] ?: 0
            map[it] = crtFreq + 1
        }
        var maxLucky = -1
        for (m in map) {
            if (m.key == m.value) {
                maxLucky = Math.max(maxLucky, m.key)
            }
        }
        return maxLucky
    }

    fun numTeams(rating: IntArray): Int {
        if (rating.size < 3) {
            return 0
        }
        var r = 0
        val size = rating.size
        for (i in 0 until size) {
            for (j in i until size) {
                for (k in j until size) {
                    val ri = rating[i]
                    val rj = rating[j]
                    val rk = rating[k]
                    if (rj in (ri + 1)..(rk - 1) || rj in (rk + 1)..(ri - 1)) {
                        r++
                    }
                }
            }
        }
        return r
    }

    class UndergroundSystem {

        val checkIns = hashMapOf<Int, Pair<String, Int>>()
        val travels = hashMapOf<String, ArrayList<Double>>()

        fun checkIn(id: Int, stationName: String, t: Int) {
            checkIns[id] = Pair(stationName, t)
        }

        fun checkOut(id: Int, dest: String, t: Int) {
            val checkIn = checkIns[id]!!
            val from = checkIn.first
            val inTime = checkIn.second
            val travelId = "${from}_$dest"
            val duration = t - inTime
            val crtTravels = travels[travelId] ?: arrayListOf()
            crtTravels.add(duration.toDouble())
            travels[travelId] = crtTravels
            checkIns.remove(id)
        }

        fun getAverageTime(startStation: String, endStation: String): Double {
            val travelId = "${startStation}_$endStation"
            val times = travels[travelId]
            return times?.average() ?: 0.toDouble()
        }

        @Test
        fun runTests() {
            val undergroundSystem = UndergroundSystem()
            undergroundSystem.checkIn(45, "Leyton", 3)
            undergroundSystem.checkIn(32, "Paradise", 8)
            undergroundSystem.checkIn(27, "Leyton", 10)
            undergroundSystem.checkOut(45, "Waterloo", 15)
            undergroundSystem.checkOut(27, "Waterloo", 20)
            undergroundSystem.checkOut(32, "Cambridge", 22)
            val r1 = undergroundSystem.getAverageTime("Paradise", "Cambridge")       // return 14.0. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)
            println(r1)
            val r2 = undergroundSystem.getAverageTime("Leyton", "Waterloo")          // return 11.0. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.0
            println(r2)
            undergroundSystem.checkIn(10, "Leyton", 24)
            val r3 = undergroundSystem.getAverageTime("Leyton", "Waterloo")          // return 11.0
            println(r3)
            undergroundSystem.checkOut(10, "Waterloo", 38)
            val r4 = undergroundSystem.getAverageTime("Leyton", "Waterloo")
            println(r4)
        }

    }

    val m = 1000000007
    val a = "a"[0]
    val z = "z"[0]

    fun findGoodStrings(n: Int, s1: String, s2: String, evil: String): Int {
        var r = 0
        var s = s1

        while (s <= s2) {
            if (!s.contains(evil)) {
                r++
            }
            s = getNext(s)
        }

        return r % m
    }

    fun getNext(s: String): String {
        val arr = s.toCharArray()
        for (i in s.length - 1 downTo 0) {
            val c = s[i]
            if (c < z) {
                arr[i] = c + 1
                break
            } else {
                arr[i] = a
            }
        }
        return String(arr)
    }

    @Test
    fun runTests() {
        println(numTeams(intArrayOf(2, 5, 3, 4, 1)))
        println(numTeams(intArrayOf(2, 3, 1)))
        println(numTeams(intArrayOf(1, 2, 3, 4)))

        println(findLucky(intArrayOf(2, 2, 3, 4)))
        println(findLucky(intArrayOf(1, 2, 2, 3, 3, 3)))
        println(findLucky(intArrayOf(2, 2, 2, 3, 3)))
        println(findLucky(intArrayOf(5)))
        println(findLucky(intArrayOf(7, 7, 7, 7, 7, 7, 7)))

        println(findGoodStrings(2, "aa", "da", "b"))
        println(findGoodStrings(8, "leetcode", "leetgoes", "leet"))
        println(findGoodStrings(2, "gx", "gz", "x"))
//        println(findGoodStrings(8, "aaaaaaaa", "zzzzzzzz", "x"))
    }
}