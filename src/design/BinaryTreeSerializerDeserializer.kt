package design

import org.junit.Test
import java.util.*

class BinaryTreeSerializerDeserializer {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    //1,3,7,15,31
    class Codec {
        fun encode(root: TreeNode?): String {
            val a = ArrayList<Int?>()
            val queue = LinkedList<TreeNode?>()
            queue.addLast(root)
            var i = 1
            while (queue.isNotEmpty()) {
                val node = queue.pop()
                a.add(node?.`val`)
                if (node != null) {
                    queue.addLast(node.left)
                    queue.addLast(node.right)
                    i += 2
                }
            }
            while (a.size > 2 && a[a.size - 1] == null && a[a.size - 2] == null) {
                a.removeAt(a.size - 1)
                a.removeAt(a.size - 1)
            }

            val builder = StringBuilder()
            builder.append("[")
            for (i in a.indices) {
                builder.append(a[i])
                if (i < a.size - 1) {
                    builder.append(",")
                }
            }
            builder.append("]")
            return builder.toString()
        }

        fun decode(data: String): TreeNode? {
            val stripped = data.substring(1, data.length - 1)
            val items = stripped.split(",")
            if (items.isEmpty()) {
                return null
            }
            val rootValue = items[0].toIntOrNull() ?: return null
            val root = TreeNode(rootValue)
            if (items.size < 2) {
                return root
            }
            val queue = LinkedList<TreeNode>()
            queue.addLast(root)

            for (i in 1 until items.size) {
                val nodeValue = items[i].toIntOrNull()
                val n = if (nodeValue != null) {
                    TreeNode(nodeValue)
                } else {
                    null
                }
                if (i % 2 == 1) {
                    val node = queue.peek()
                    node.left = n
                } else {
                    val node = queue.pop()
                    node.right = n
                }
                if (n != null) {
                    queue.addLast(n)
                }
            }
            return root
        }
    }

    @Test
    fun runTest() {
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        root.right?.left = TreeNode(4)
        root.right?.right = TreeNode(5)

        val obj = Codec()
        val data = obj.encode(root)
        println(data)
        val node = obj.decode(data)
        println(node)
    }
}