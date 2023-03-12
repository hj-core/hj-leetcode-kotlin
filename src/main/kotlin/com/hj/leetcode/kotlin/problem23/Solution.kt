package com.hj.leetcode.kotlin.problem23

import com.hj.leetcode.kotlin.common.model.ListNode
import java.util.*

/**
 * LeetCode page: [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogK) and Space O(K) where N is the total nodes of lists and K is the size of lists;
     */
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val dummyHead = ListNode(-1)
        var currTail = dummyHead

        val nodePq = PriorityQueue<ListNode>(compareBy { it.`val` })
        for (list in lists) {
            list?.let { nodePq.add(it) }
        }

        while (nodePq.isNotEmpty()) {
            val minNode = nodePq.poll()
            minNode.next?.let { nodePq.add(it) }
            currTail.next = minNode
            currTail = minNode // i.e. currTail.next
        }
        return dummyHead.next
    }
}