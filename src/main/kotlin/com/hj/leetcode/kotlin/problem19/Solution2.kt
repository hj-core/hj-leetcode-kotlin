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
        var slow = head
        var fast = head.getNthNode(n + 1)

        val isRemovingHead = fast == null
        if (isRemovingHead) return head?.next

        while (fast?.next != null) {
            fast = fast.next
            slow = slow?.next
        }

        slow?.next = slow?.next?.next
        return head
    }

    private fun ListNode?.getNthNode(n: Int): ListNode? {
        var position = 1
        var node = this

        while (position < n && node != null) {
            position++
            node = node.next
        }
        return node
    }
}