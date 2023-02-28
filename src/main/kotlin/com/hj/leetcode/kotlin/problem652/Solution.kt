package com.hj.leetcode.kotlin.problem652

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [652. Find Duplicate Subtrees](https://leetcode.com/problems/find-duplicate-subtrees/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
        if (root == null) return emptyList()

        val result = mutableListOf<TreeNode>()
        val state = State()

        postOrderTraversal(root) {
            state.update(it)
            val id = checkNotNull(state.nodeToId[it])
            val freq = checkNotNull(state.idFrequency[id])
            if (freq == 2) result.add(it)
        }
        return result
    }

    private class State(
        val nodeToId: MutableMap<TreeNode, Int> = hashMapOf(),
        val idFrequency: MutableMap<Int, Int> = hashMapOf()
    ) {
        private val stringFromToId: MutableMap<String, Int> = hashMapOf()

        fun update(node: TreeNode) {
            if (nodeToId.containsKey(node)) return

            node.left?.let { update(it) }
            node.right?.let { update(it) }

            val leftId = nodeToId[node.left] ?: -1
            val rightId = nodeToId[node.right] ?: -1
            val stringForm = "$leftId, ${node.`val`}, $rightId"

            val id = stringFromToId.computeIfAbsent(stringForm) { stringFromToId.size }
            nodeToId[node] = id
            idFrequency[id] = (idFrequency[id] ?: 0) + 1
        }
    }

    private fun postOrderTraversal(root: TreeNode?, sideEffect: (node: TreeNode) -> Unit) {
        if (root == null) return
        postOrderTraversal(root.left, sideEffect)
        postOrderTraversal(root.right, sideEffect)
        sideEffect(root)
    }
}