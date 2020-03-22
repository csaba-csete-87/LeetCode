import org.junit.Test

class AddTwoNumbers {

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var n1 = l1
        var n2 = l2
        var nr: ListNode? = null
        var nr1: ListNode? = null
        var a = 0
        while (n1 != null || n2 != null || a > 0) {
            val n1Val = n1?.`val` ?: 0
            val n2Val = n2?.`val` ?: 0
            val r = n1Val + n2Val
            val rTrim = (r + a) % 10
            if (nr == null) {
                nr = ListNode(rTrim)
                nr1 = nr
            } else {
                nr.next = ListNode(rTrim)
                nr = nr.next
            }
            a = if (r + a > 9) 1 else 0
            n1 = n1?.next
            n2 = n2?.next
        }
        return nr1
    }

    @Test
    fun runTests() {
        val l1a = ListNode(2)
        val l1b = ListNode(4)
        val l1c = ListNode(3)
        l1a.next = l1b
        l1b.next = l1c

        val l2a = ListNode(5)
        val l2b = ListNode(6)
        val l2c = ListNode(4)
        l2a.next = l2b
        l2b.next = l2c

//        val result = addTwoNumbers(l1a, l2a)
//        println(result)
//
//        val l1a = ListNode(1)
//        val l2a = ListNode(9)
//        val l2b = ListNode(9)
//        l2a.next = l2b
//        val result = addTwoNumbers(l1a, l2a)
//        println(result)
    }

}