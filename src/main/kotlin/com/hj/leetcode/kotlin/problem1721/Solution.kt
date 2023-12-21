package com.hj.leetcode.kotlin.problem1721

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [1721. Swapping Nodes in a Linked List](https://leetcode.com/problems/swapping-nodes-in-a-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun swapNodes(head: ListNode?, k: Int): ListNode? {
        if (head == null) {
            return null
        }

        val kthNode = kthNode(head, k)
        val kthNodeFromEnd = kthNodeFromEnd(head, kthNode)
        swapValue(kthNode, kthNodeFromEnd)
        return head
    }

    private fun kthNode(head: ListNode, k: Int): ListNode {
        var currentPosition = 1
        var currentNode = head

        while (currentPosition < k) {
            currentPosition++
            currentNode = checkNotNull(currentNode.next)
        }
        return currentNode
    }

    private fun kthNodeFromEnd(head: ListNode, kthNode: ListNode): ListNode {
        /* Use two-pointers technique to find the kth node from the end.
         * The invariance that for each node the sum of its one-indexed position from the beginning and
         * from the end equals the size of the head plus one helps to justify the correctness.
         */
        var slow = head
        var fast = kthNode.next

        while (fast != null) {
            slow = checkNotNull(slow.next)
            fast = fast.next
        }
        return slow
    }

    private fun swapValue(node: ListNode, withNode: ListNode) {
        node.`val` = withNode.`val`.also { withNode.`val` = node.`val` }
    }
}