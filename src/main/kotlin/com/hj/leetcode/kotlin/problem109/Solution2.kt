package com.hj.leetcode.kotlin.problem109

import com.hj.leetcode.kotlin.common.model.ListNode
import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [109. Convert Sorted List to Binary Search Tree](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(LogN) where N is the number of nodes in head;
     */
    fun sortedListToBST(head: ListNode?): TreeNode? {
        val lastIndex = head.size() - 1
        val nodePointer = NodePointer(head)
        return buildBstFromSortedList(0, lastIndex, nodePointer)
    }

    private fun ListNode?.size(): Int {
        var size = 0
        var currNode = this
        while (currNode != null) {
            size++
            currNode = currNode.next
        }
        return size
    }

    private class NodePointer(var node: ListNode?) {

        fun getValue() = checkNotNull(node).`val`

        fun moveToNext() {
            node = node?.next
        }
    }

    private fun buildBstFromSortedList(
        fromIndex: Int,
        toIndex: Int,
        inOrderPointer: NodePointer
    ): TreeNode? {

        if (fromIndex > toIndex) return null
        if (fromIndex == toIndex) return TreeNode(inOrderPointer.getValue())

        val midIndex = (fromIndex + toIndex + 1) ushr 1
        val leftTree = buildBstFromSortedList(fromIndex, midIndex - 1, inOrderPointer)

        inOrderPointer.moveToNext()
        val midValue = inOrderPointer.getValue()

        var rightTree: TreeNode? = null
        val hasRightTree = toIndex > midIndex
        if (hasRightTree) {
            inOrderPointer.moveToNext()
            rightTree = buildBstFromSortedList(midIndex + 1, toIndex, inOrderPointer)
        }

        return TreeNode(midValue).apply {
            left = leftTree
            right = rightTree
        }
    }
}