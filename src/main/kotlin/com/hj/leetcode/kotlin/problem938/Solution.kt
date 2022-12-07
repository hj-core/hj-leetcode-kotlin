package com.hj.leetcode.kotlin.problem938

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [938. Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N and H are the number of nodes and height of root;
     */
    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        return root?.run {
            when {
                `val` < low -> rangeSumBST(right, low, high)
                `val` > high -> rangeSumBST(left, low, high)
                else -> `val` + rangeSumBST(left, low, high) + rangeSumBST(right, low, high)
            }
        } ?: 0
    }
}