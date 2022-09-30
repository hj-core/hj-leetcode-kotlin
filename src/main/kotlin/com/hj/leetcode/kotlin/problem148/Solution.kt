package com.hj.leetcode.kotlin.problem148

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [148. Sort List](https://leetcode.com/problems/sort-list/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(LogN) where N is the number of nodes in head;
     */
    fun sortList(head: ListNode?): ListNode? {
        val lessThenTwoNodes = head?.next == null
        if (lessThenTwoNodes) return head

        val midNode = head.splitAtMidAndReturnMidNode()
        return mergeSortedList(sortList(head), sortList(midNode))
    }

    private fun ListNode?.splitAtMidAndReturnMidNode(): ListNode? {
        val nodeBeforeMid = getNodeBeforeMid()
        val midNode = nodeBeforeMid?.next

        nodeBeforeMid?.next = null
        return midNode
    }

    private fun ListNode?.getNodeBeforeMid(): ListNode? {
        var slow = this
        var fast = this?.next

        while (fast?.next?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }
        return slow
    }

    private fun mergeSortedList(sorted1: ListNode?, sorted2: ListNode?): ListNode? {
        val dummyHead = ListNode(-1)

        var currTail = dummyHead
        var ptr1 = sorted1
        var ptr2 = sorted2

        while (ptr1 != null && ptr2 != null) {
            if (ptr1.`val` < ptr2.`val`) {
                currTail.next = ptr1
                ptr1 = ptr1.next
            } else {
                currTail.next = ptr2
                ptr2 = ptr2.next
            }

            currTail = checkNotNull(currTail.next)
        }

        currTail.next = ptr1 ?: ptr2
        return dummyHead.next
    }
}