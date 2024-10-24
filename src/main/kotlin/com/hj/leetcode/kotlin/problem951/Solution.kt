package com.hj.leetcode.kotlin.problem951

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [951. Flip Equivalent Binary Trees](https://leetcode.com/problems/flip-equivalent-binary-trees/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the smaller number of nodes between root1 and root2
     * and H is the smaller height between root1 and root2.
     */
    fun flipEquiv(
        root1: TreeNode?,
        root2: TreeNode?,
    ): Boolean {
        if (root1 == null || root2 == null) {
            return root1 == root2
        }
        if (root1.`val` != root2.`val`) {
            return false
        }

        val left1 = root1.left
        val right1 = root1.right
        require(left1?.let { it.`val` != right1?.`val` } ?: true) {
            "Each node should have unique value by the problem constraint."
        }
        val left2 = root2.left
        val right2 = root2.right
        return if (left1?.`val` == left2?.`val`) {
            flipEquiv(left1, left2) && flipEquiv(right1, right2)
        } else {
            flipEquiv(left1, right2) && flipEquiv(right1, left2)
        }
    }
}
