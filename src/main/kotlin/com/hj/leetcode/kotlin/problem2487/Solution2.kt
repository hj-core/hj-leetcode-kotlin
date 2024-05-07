package com.hj.leetcode.kotlin.problem2487

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2487. Remove Nodes From Linked List](https://leetcode.com/problems/remove-nodes-from-linked-list/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in head;
     */
    fun removeNodes(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }
        val removedNext = removeNodes(head.next)

        return when {
            removedNext == null -> head
            head.`val` < removedNext.`val` -> removedNext
            else -> {
                head.next = removedNext
                head
            }
        }
    }
}