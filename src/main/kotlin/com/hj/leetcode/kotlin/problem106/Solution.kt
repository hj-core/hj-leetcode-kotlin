package com.hj.leetcode.kotlin.problem106

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [106. Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of inorder;
     */
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        val inorderValueToIndex = inorder.valueToIndex()

        require(inorderValueToIndex.size == inorder.size) {
            "All node values should be distinct by problem constraints."
        }

        return buildTree(SubArray(inorder), inorderValueToIndex, SubArray(postorder))
    }

    private fun IntArray.valueToIndex(): Map<Int, Int> {
        return foldIndexed(hashMapOf()) { index, acc, i ->
            acc[i] = index
            acc
        }
    }

    private class SubArray(
        val fullArray: IntArray,
        val from: Int = 0,
        val to: Int = fullArray.lastIndex
    ) {
        val size = to - from + 1
    }

    private fun buildTree(
        inorder: SubArray,
        inorderValueToIndex: Map<Int, Int>,
        postorder: SubArray
    ): TreeNode? {

        val isEmpty = with(inorder) { from > to }
        if (isEmpty) return null

        val rootValue = with(postorder) { fullArray[to] }
        val leftSize = checkNotNull(inorderValueToIndex[rootValue]) - inorder.from
        val rightSize = inorder.size - leftSize - 1

        return TreeNode(rootValue).apply {
            left = buildTree(
                with(inorder) { SubArray(fullArray, from, from + leftSize - 1) },
                inorderValueToIndex,
                with(postorder) { SubArray(fullArray, from, from + leftSize - 1) }
            )

            right = buildTree(
                with(inorder) { SubArray(fullArray, to - rightSize + 1, to) },
                inorderValueToIndex,
                with(postorder) { SubArray(fullArray, to - rightSize , to -1) }
            )
        }
    }
}