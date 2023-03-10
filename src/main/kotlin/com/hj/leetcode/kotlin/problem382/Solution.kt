package com.hj.leetcode.kotlin.problem382

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [382. Linked List Random Node](https://leetcode.com/problems/linked-list-random-node/);
 */
class Solution(head: ListNode?) {

    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in head;
     */
    private val list = head.toList()

    private fun ListNode?.toList(): List<Int> {
        val result = mutableListOf<Int>()
        var currNode = this
        while (currNode != null) {
            result.add(currNode.`val`)
            currNode = currNode.next
        }
        return result
    }

    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun getRandom(): Int {
        return list.random()
    }
}