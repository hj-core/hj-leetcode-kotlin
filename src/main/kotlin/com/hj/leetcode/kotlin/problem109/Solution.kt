package com.hj.leetcode.kotlin.problem109

import com.hj.leetcode.kotlin.common.model.ListNode
import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [109. Convert Sorted List to Binary Search Tree](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in head;
     */
    fun sortedListToBST(head: ListNode?): TreeNode? {
        val sorted = head.toList()
        return buildTree(sorted)
    }

    private fun ListNode?.toList(): List<Int> {
        val result = mutableListOf<Int>()
        var currNode = this
        while (currNode != null) {
            result.add(currNode.`val`)
            currNode = currNode.next
        }
        return result
    }

    private fun buildTree(
        sortedList: List<Int>,
        from: Int = 0,
        to: Int = sortedList.lastIndex
    ): TreeNode? {

        if (from > to) return null
        if (from == to) return TreeNode(sortedList[from])

        val mid = (from + to + 1) ushr 1
        val result = TreeNode(sortedList[mid]).apply {
            left = buildTree(sortedList, from, mid - 1)
            right = buildTree(sortedList, mid + 1, to)
        }
        return result
    }
}