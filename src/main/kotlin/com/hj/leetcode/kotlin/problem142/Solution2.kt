package com.hj.leetcode.kotlin.problem142

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in head;
     */
    fun detectCycle(head: ListNode?): ListNode? {
        var currNode = head
        val visited = hashSetOf<ListNode>()

        while (currNode != null && currNode !in visited) {
            visited.add(currNode)
            currNode = currNode.next
        }
        return currNode
    }
}