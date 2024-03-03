package com.hj.leetcode.kotlin.problem19

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummyHead = ListNode(0).apply { next = head }
        var slow = dummyHead
        var fast = head.getOrNull(n)

        while (fast != null) {
            fast = fast.next
            slow = checkNotNull(slow.next)
        }
        val nodeToRemove = slow.next
        slow.next = nodeToRemove?.next
        nodeToRemove?.next = null
        return dummyHead.next
    }

    private fun ListNode?.getOrNull(index: Int): ListNode? {
        var result = this
        var i = 0

        while (i < index && result != null) {
            result = result.next
            i++
        }
        return result
    }
}