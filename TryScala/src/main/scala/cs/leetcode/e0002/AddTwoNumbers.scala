package cs.leetcode.e0002

/**
 * https://books.halfrost.com/leetcode/ChapterFour/0001~0099/0002.Add-Two-Numbers/
 * 用链表存储整数，每个节点存储整数中的一位，且逆序存储。求2个链表的和
 */
object AddTwoNumbers extends App{

    /**
     *  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
        Output: 7 -> 0 -> 8
        Explanation: 342 + 465 = 807.
     * @param num1
     * @param num2
     * @return
     */
    def add(num1: Node, num2:Node): Node = {
        var (l1, l2) = (num1, num2)
        val head = Node(0, null)
        var (n1, n2, carry, current) = (0, 0, 0, head)
        while (l1 != null || l2 != null || carry != 0){
            if(l1 == null) {
                n1 = 0
            }else {
                n1 = l1.value
                l1 = l1.next
            }

            if(l2 == null) {
                n2 = 0
            }else {
                n2 = l2.value
                l2 = l2.next
            }

            current.next = Node(value = (n1 + n2 + carry) % 10, next = null)
            current = current.next
            carry = (n1 + n2 + carry) / 10
        }
        head.next
    }

    val sum = add(Node(2, Node(4, Node(3, null))),
        Node(5, Node(6, Node(4, null))))
    assert("807" == sum.getValueList().mkString("")) // 807
}
case class Node(value:Int, var next: Node){
    def print(): Unit = {
        println(getValueList().reverse.mkString(" -> "))
    }

    def getValueList(): List[Int] ={
        var c = this
        var l = List.empty[Int]
        while (c!=null){
            l = c.value::l
            c = c.next
        }
        l
    }
}


