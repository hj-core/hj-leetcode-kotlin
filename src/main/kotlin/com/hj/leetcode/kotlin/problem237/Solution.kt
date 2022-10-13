package com.hj.leetcode.kotlin.problem237

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [237. Delete Node in a Linked List](https://leetcode.com/problems/delete-node-in-a-linked-list/);
 *
 * Note: Although this is the official solution, I don't think it is deleting a node. The object reference is
 * wrong, and it is more like deleting a value.
 */
class Solution {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun deleteNode(node: ListNode?) {
        requireNotNull(node)

        val nextNode = checkNotNull(node.next)
        node.`val` = nextNode.`val`
        node.next = nextNode.next

        nextNode.next = null
    }
}