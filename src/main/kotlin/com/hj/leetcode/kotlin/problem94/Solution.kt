package com.hj.leetcode.kotlin.problem94

import com.hj.leetcode.kotlin.common.model.TreeNode

class Solution {
    /* Complexity:
     * Time O(N) and Space O(LogN) where N is the number of nodes in root;
     */
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val ans = mutableListOf<Int>()
        root.inorderTraversal { nodeValue -> ans.add(nodeValue) }
        return ans
    }

    private fun TreeNode?.inorderTraversal(sideEffect: (nodeValue: Int) -> Unit) {
        if (this != null) {
            left.inorderTraversal(sideEffect)
            sideEffect(`val`)
            right.inorderTraversal(sideEffect)
        }
    }
}