package com.hj.leetcode.kotlin.problem100

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [100. Same Tree](https://leetcode.com/problems/same-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the min of p's and q's size and H is the min of p's and
     * q's height;
     */
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return when {
            p == null || q == null -> p == q
            p.`val` != q.`val` -> false
            else -> isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }
}