package linked_lists

import ListNode
import org.junit.Test

@ExperimentalStdlibApi
class RemoveNthNodeFromEnd {

    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var node = head
        var runner: ListNode? = null
        var cnt = 0
        while (node != null) {
            if (runner != null) {
                runner = runner.next
            }
            if (cnt == n) {
                runner = head
            }
            node = node.next
            cnt++
        }
        println("---")
        println(runner?.`val`)
        return if (runner == null) {
            head?.next
        } else {
            runner.next = runner.next?.next
            head
        }
    }

    @Test
    fun runTests() {
        val lll1 = ListNode(1)
        val lll2 = ListNode(2)
        lll1.next = lll2
        removeNthFromEnd(lll1, 2)

        val ll1 = ListNode(1)
        removeNthFromEnd(ll1, 1)

        val l1 = ListNode(1)
        val l2 = ListNode(2)
        val l3 = ListNode(3)
        val l4 = ListNode(4)
        val l5 = ListNode(5)
        l1.next = l2
        l2.next = l3
        l3.next = l4
        l4.next = l5
        removeNthFromEnd(l1, 2)
    }
}