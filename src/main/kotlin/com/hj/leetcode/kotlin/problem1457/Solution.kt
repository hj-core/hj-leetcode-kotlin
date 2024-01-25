package com.hj.leetcode.kotlin.problem1457

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [1457. Pseudo-Palindromic Paths in a Binary Tree](https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun pseudoPalindromicPaths(root: TreeNode?): Int {
        var result = 0
        dfs(root) { pathValueCount ->
            val isPseudoPalindromic = pathValueCount.count { it % 2 == 1 } <= 1
            if (isPseudoPalindromic) {
                result++
            }
        }
        return result
    }

    private fun dfs(
        root: TreeNode?,
        pathValueCount: IntArray = IntArray(10),
        onEachLeaf: (pathValueCount: IntArray) -> Unit,
    ) {
        if (root == null) {
            return
        }
        pathValueCount[root.`val`]++
        if (root.isLeaf()) {
            onEachLeaf(pathValueCount)
        } else {
            dfs(root.left, pathValueCount, onEachLeaf)
            dfs(root.right, pathValueCount, onEachLeaf)
        }
        pathValueCount[root.`val`]--
    }

    private fun TreeNode.isLeaf() = this.left == null && this.right == null
}