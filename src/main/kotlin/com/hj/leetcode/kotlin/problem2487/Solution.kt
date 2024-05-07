package com.hj.leetcode.kotlin.problem2487

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2487. Remove Nodes From Linked List](https://leetcode.com/problems/remove-nodes-from-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in head;
     */
    fun removeNodes(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        val stack = mutableListOf<ListNode>()
        var nodePtr = head
        while (nodePtr != null) {
            while (stack.isNotEmpty() && stack.last().`val` < nodePtr.`val`) {
                stack.removeLast()
            }
            stack.add(nodePtr)
            nodePtr = nodePtr.next
        }

        for (i in 0..<stack.lastIndex) {
            stack[i].next = stack[i + 1]
        }
        return stack[0]
    }
}