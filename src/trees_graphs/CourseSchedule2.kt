package trees_graphs

import org.junit.Test
import java.util.*

class CourseSchedule2 {
    class Node(val value: Int) {
        val pre = hashMapOf<Int, Node>()
    }

    class Graph {
        val allNodes = hashMapOf<Int, Node>()
        val topNodes = hashMapOf<Int, Node>()

        fun insert(value: Int, pre: Int): Boolean {
            if (!allNodes.containsKey(value)) {
                allNodes[value] = Node(value)
            }
            if (!allNodes.containsKey(pre)) {
                allNodes[pre] = Node(pre)
            }
            if (topNodes.containsKey(pre)) {
                topNodes.remove(pre)
            }
            val crtNode = allNodes[value]!!
            val preNode = allNodes[pre]!!
            if (preNode.pre.containsKey(value)) {
                return false
            }
            crtNode.pre[pre] = preNode
            topNodes[value] = crtNode
            return true
        }
    }

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val allCourses = hashSetOf<Int>()
        for (c in 0 until numCourses) {
            allCourses.add(c)
        }
        val graph = Graph()
        prerequisites.forEach {
            val isSuccess = graph.insert(it[0], it[1])
            if (!isSuccess) {
                return intArrayOf()
            }
        }
        val results = arrayListOf<Int>()
        val visited = hashSetOf<Int>()
        for (n in graph.topNodes.values) {
            val r = arrayListOf<Int>()
            val q = LinkedList<Node>()
            q.addLast(n)
            while (q.size > 0) {
                val crtNode = q.pop()
                if (!visited.contains(crtNode.value)) {
                    r.add(crtNode.value)
                    visited.add(crtNode.value)
                    for (crtPrereq in crtNode.pre.values) {
                        q.addLast(crtPrereq)
                        for (prepre in crtPrereq.pre.values) {
                            if (visited.contains(prepre.value)) {
                                return intArrayOf()
                            }
                        }
                    }
                }
            }
            results.addAll(r.reversed())
        }
        for (course in allCourses) {
            if (!visited.contains(course)) {
                results.add(course)
            }
        }
        return results.toIntArray()
    }

    @Test
    fun runTest() {
        val r3 = findOrder(
            2,
            arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 2),
                intArrayOf(2, 1)
            )
        )
        val r2 = findOrder(
            2,
            arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 1)
            )
        )
        val r1 = findOrder(
            4,
            arrayOf(
                intArrayOf(1, 0),
                intArrayOf(2, 0),
                intArrayOf(3, 1),
                intArrayOf(3, 2)
            )
        )
        print(r1)
    }
}