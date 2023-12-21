package com.hj.leetcode.kotlin.problem142

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun detectCycle(head: ListNode?): ListNode? {
        var meetNode = findNodeWhereSlowFastPointersMeet(head)
        val noCycleExists = meetNode == null
        if (noCycleExists) return null

        var cycleStart = head
        while (cycleStart != meetNode) {
            meetNode = meetNode?.next
            cycleStart = cycleStart?.next
        }
        return cycleStart
    }

    private fun findNodeWhereSlowFastPointersMeet(head: ListNode?): ListNode? {
        var slow = head?.next
        var fast = head?.next?.next
        while (fast != null && fast != slow) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
    }
}