package com.hj.leetcode.kotlin.problem2095

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2095. Delete the Middle Node of a Linked List](https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun deleteMiddle(head: ListNode?): ListNode? {
        val nodeBeforeMid = findNodeBeforeMid(head) ?: return null

        nodeBeforeMid.next = nodeBeforeMid.next?.next
        return head
    }

    private fun findNodeBeforeMid(head: ListNode?): ListNode? {
        if (head?.next == null) return null

        var slow = head
        var fast = head.next?.next

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
    }
}