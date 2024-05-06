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

        val list = head.toList()
        var suffixMax = list.last().`val`
        var result: ListNode? = null

        for (node in list.asReversed()) {
            if (node.`val` < suffixMax) {
                continue
            }
            node.next = result
            result = node
            suffixMax = node.`val`
        }
        return result
    }

    private fun ListNode?.toList(): List<ListNode> = buildList {
        var nodePtr = this@toList
        while (nodePtr != null) {
            add(nodePtr)
            nodePtr = nodePtr.next
        }
    }
}