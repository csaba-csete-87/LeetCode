import org.junit.Test
import java.util.*
import kotlin.test.assertEquals


class FruitsIntoBaskets {

    class MyLinkedList {
        var head: Node? = null
        var tail: Node? = null

        fun insert(value: Int) {
            val newNode = Node(value)
            if (head == null) {
                head = newNode
                tail = newNode
            } else {
                tail?.next = newNode
                newNode.prev = tail
                tail = newNode
            }
        }
    }

    class Node(val data: Int) {
        var next: Node? = null
        var prev: Node? = null
        var cnt: Int = 0
    }

    fun totalFruitV1(trees: IntArray): Int {
        val linkedList = MyLinkedList()

        var max = 0
        for (tree in trees) {
            var last = linkedList.tail
            var prev = last?.prev
            if (last == null) {
                linkedList.insert(tree)
                last = linkedList.tail
                prev = linkedList.tail?.prev
            } else if (prev == null && tree != last.data) {
                linkedList.insert(tree)
                last = linkedList.tail
                prev = linkedList.tail?.prev
            }
            if (tree != last?.data && tree != prev?.data) {
                linkedList.insert(tree)
                last = linkedList.tail
                prev = linkedList.tail?.prev
            }
            when (tree) {
                last?.data -> last.cnt++
                prev?.data -> prev.cnt++
            }

            printTree(linkedList)

            val lastCnt = last?.cnt ?: 0
            val prevCnt = prev?.cnt ?: 0
            val crtMax = lastCnt + prevCnt
            if (crtMax > max) {
                max = crtMax
            }
        }
        return max
    }

    fun totalFruit(trees: IntArray): Int {
        if (trees.size < 3) {
            return trees.size
        } else {
            var max = 2

            var x = trees[0]
            var y = trees[1]
            for (i in 2 until trees.size) {

            }
            return max
        }
    }

    fun totalFruitAnswer(tree: IntArray): Int {
        var ans = 0
        var i = 0
        val count = Counter()
        for (j in tree.indices) {
            count.add(tree[j], 1)
            while (count.size >= 3) {
                count.add(tree[i], -1)
                if (count[tree[i]] == 0)
                    count.remove(tree[i])
                i++
            }

            ans = Math.max(ans, j - i + 1)
        }

        return ans
    }

    internal inner class Counter : HashMap<Int, Int>() {
        override fun get(k: Int): Int {
            return if (containsKey(k)) super.get(k) ?: 0 else 0
        }

        fun add(k: Int, v: Int) {
            put(k, get(k) + v)
        }
    }

    private fun printTree(linkedList: MyLinkedList) {
        var node = linkedList.head
        while (node != null) {
            print("${node.data} -> ")
            node = node.next
        }
        println()
    }

    @Test
    fun runTests() {
        assertEquals(3, totalFruitAnswer(intArrayOf(1, 2, 1)))
        assertEquals(3, totalFruitAnswer(intArrayOf(0, 1, 2, 2)))
        assertEquals(4, totalFruitAnswer(intArrayOf(1, 2, 3, 2, 2)))
        assertEquals(5, totalFruitAnswer(intArrayOf(3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4)))
        assertEquals(2, totalFruitAnswer(intArrayOf(1, 1)))
        assertEquals(5, totalFruitAnswer(intArrayOf(1, 0, 1, 4, 1, 4, 1, 2, 3)))
        assertEquals(1, totalFruitAnswer(intArrayOf(1)))
        assertEquals(2, totalFruitAnswer(intArrayOf(0, 1, 2)))
        assertEquals(3, totalFruitAnswer(intArrayOf(0, 1, 1, 2)))
        assertEquals(4, totalFruitAnswer(intArrayOf(0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5)))
    }
}