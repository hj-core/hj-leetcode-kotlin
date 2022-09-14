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
        var pseudoPalindromicPaths = 0
        traversePreOrder(root) { pathDigitFrequency ->
            if (isPseudoPalindromic(pathDigitFrequency)) pseudoPalindromicPaths++
        }
        return pseudoPalindromicPaths
    }

    private fun traversePreOrder(
        root: TreeNode?,
        pathDigitFrequency: IntArray = IntArray(10),
        sideEffectAtLeaf: (pathDigitFrequency: IntArray) -> Unit
    ) {
        if (root == null) return
        pathDigitFrequency[root.`val`]++

        if (root.isLeaf()) {
            sideEffectAtLeaf(pathDigitFrequency)
        } else {
            traversePreOrder(root.left, pathDigitFrequency, sideEffectAtLeaf)
            traversePreOrder(root.right, pathDigitFrequency, sideEffectAtLeaf)
        }
        pathDigitFrequency[root.`val`]--
    }

    private fun TreeNode.isLeaf() = this.left == null && this.right == null

    private fun isPseudoPalindromic(pathDigitFrequency: IntArray): Boolean {
        val oddFrequencies = pathDigitFrequency.count { freq -> freq and 1 == 1 }
        return oddFrequencies <= 1
    }
}