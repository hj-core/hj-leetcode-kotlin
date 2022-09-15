package com.hj.leetcode.kotlin.problem143

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [143. Reorder List](https://leetcode.com/problems/reorder-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun reorderList(head: ListNode?): Unit {
        val midNode = getMidNodeOfLinkedList(head = head)
        /* Reminder: The LinkedList is not linear after revert the second half, the node before original midNode
         * still link to the original midNode;
         */
        val newMidNode = revertLinkedListAndReturnNewHead(head = midNode)
        reorderLinkedListAsRequired(head, newMidNode)
    }

    private fun getMidNodeOfLinkedList(head: ListNode?): ListNode? {
        var fastPointer = head
        var slowPointer = head

        while (fastPointer?.next != null) {
            fastPointer = fastPointer.next?.next
            slowPointer = slowPointer?.next
        }
        return slowPointer
    }

    private fun revertLinkedListAndReturnNewHead(head: ListNode?): ListNode? {
        if (head == null) return null
        var newHead = head
        var nodeFollowOriginalHead = head.next

        while (nodeFollowOriginalHead != null) {
            head.next = nodeFollowOriginalHead.next
            nodeFollowOriginalHead.next = newHead
            newHead = nodeFollowOriginalHead
            nodeFollowOriginalHead = head.next
        }
        return newHead
    }

    private fun reorderLinkedListAsRequired(head: ListNode?, midNodeAfterRevertSecondHalf: ListNode?) {
        if (head == null) return
        var firstHalfPointer = head
        var secondHalfPointer = midNodeAfterRevertSecondHalf

        while (secondHalfPointer?.next != null) {
            val nextFirstHalfPointer = firstHalfPointer?.next
            val nextSecondHalfPointer = secondHalfPointer.next
            firstHalfPointer?.next = secondHalfPointer
            secondHalfPointer.next = nextFirstHalfPointer
            firstHalfPointer = nextFirstHalfPointer
            secondHalfPointer = nextSecondHalfPointer
        }
    }
}