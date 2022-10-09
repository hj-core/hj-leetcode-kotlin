package com.hj.leetcode.kotlin.problem653

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [653. Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        val valueVisited = hashSetOf<Int>()
        return find(k, root, valueVisited)
    }

    private fun find(targetSum: Int, bstRoot: TreeNode?, valueVisited: MutableSet<Int>): Boolean {
        if (bstRoot == null) return false

        val complementary = targetSum - bstRoot.`val`
        if (complementary in valueVisited) return true

        valueVisited.add(bstRoot.`val`)
        return find(targetSum, bstRoot.left, valueVisited) || find(targetSum, bstRoot.right, valueVisited)
    }
}