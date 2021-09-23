package com.rock.datastructure.list

/**
 * https://segmentfault.com/a/1190000037518253
 */
object ReverseListSolution {

  def reverseListRecurse[T](head: ListNode[T]): ListNode[T] = {
    if (head == null || head.next == null) {
      return head
    }
    val newHead = reverseListRecurse(head.next)
    head.next.next = head
    head.next = null
    newHead
  }
}
case class ListNode[T](data:T, var next:ListNode[T])