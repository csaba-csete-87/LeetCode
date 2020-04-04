package design

import org.junit.Test
import java.util.*
import kotlin.collections.HashMap


class LRUCacheExercise {

    class LRUCache(private val capacity: Int) {

        private val linkedList = LinkedList<Int>()
        private val map = HashMap<Int, Int>()

        fun get(key: Int): Int {
            if (map.containsKey(key)) {
                val i = linkedList.indexOf(key)
                val e = linkedList[i]
                linkedList.removeAt(i)
                linkedList.addFirst(e)
                return map[key]!!
            } else {
                return -1
            }
        }

        fun put(key: Int, value: Int) {
            if (!map.containsKey(key) && map.size >= capacity) {
                val last = linkedList.last
                map.remove(last)
            }
            map[key] = value
            if(linkedList.contains(key)) {
                linkedList.remove(key)
            }
            if (linkedList.size >= capacity) {
                linkedList.removeLast()
            }
            linkedList.addFirst(key)
        }
    }

    @Test
    fun testCache() {
        val cache = LRUCache(2 /* capacity */)
        cache.put(1, 1)
        cache.put(2, 2)
        println(cache.get(1))
        cache.put(3, 3)
        println(cache.get(2))
        cache.put(4, 4)
        println(cache.get(1))
        println(cache.get(3))
        println(cache.get(4))

        println("-----")
        val cache2 = LRUCache(2)
        cache2.put(2, 1)
        cache2.put(1, 1)
        cache2.put(2, 3)
        cache2.put(4, 1)
        println(cache2.get(1))
        println(cache2.get(2))

        println("-----")
        val cache3 = LRUCache(2)
        println(cache3.get(2))
        cache3.put(2, 6)
        println(cache3.get(1))
        cache3.put(1, 5)
        cache3.put(1, 2)
        println(cache3.get(1))
        println(cache3.get(2))
    }
}