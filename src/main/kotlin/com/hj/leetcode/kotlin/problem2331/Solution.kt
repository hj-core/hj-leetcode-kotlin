package com.hj.leetcode.kotlin.problem2331

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2331. Evaluate Boolean Binary Tree](https://leetcode.com/problems/evaluate-boolean-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun evaluateTree(root: TreeNode?): Boolean = when (root?.`val`) {
        0 -> false
        1 -> true
        2 -> evaluateTree(root.left) || evaluateTree(root.right)
        3 -> evaluateTree(root.left) && evaluateTree(root.right)
        else -> throw IllegalArgumentException()
    }
}