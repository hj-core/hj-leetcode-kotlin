package com.hj.leetcode.kotlin.problem230

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [230. Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/);
 */
class Solution {
    /* Complexity:
     * Time O(H+k) and Space O(H) where H is the height of root;
     */
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        var count = 0
        var currValue = root?.`val` ?: throw IllegalStateException()

        traverseInOrder(root) { nodeValue ->
            if (count == k) return@traverseInOrder
            count++
            currValue = nodeValue
        }
        return currValue
    }

    private fun traverseInOrder(root: TreeNode?, sideEffect: (nodeValue: Int) -> Unit) {
        if (root != null) {
            traverseInOrder(root.left, sideEffect)
            sideEffect(root.`val`)
            traverseInOrder(root.right, sideEffect)
        }
    }
}