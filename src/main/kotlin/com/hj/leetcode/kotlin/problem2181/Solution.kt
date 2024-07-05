package com.hj.leetcode.kotlin.problem2181

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2181. Merge Nodes in Between Zeros](https://leetcode.com/problems/merge-nodes-in-between-zeros/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun mergeNodes(head: ListNode?): ListNode? {
        requireNotNull(head)
        var nodePtr = head.next
        var segmentSum = 0
        var resultEnd: ListNode = head

        while (nodePtr != null) {
            if (nodePtr.`val` == 0) {
                nodePtr.`val` = segmentSum
                resultEnd.next = nodePtr
                resultEnd = nodePtr
                segmentSum = 0
            } else {
                segmentSum += nodePtr.`val`
            }
            nodePtr = nodePtr.next
        }
        return head.next
    }
}