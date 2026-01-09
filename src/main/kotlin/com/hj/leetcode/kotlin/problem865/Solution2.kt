package com.hj.leetcode.kotlin.problem865

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [865. Smallest Subtree with all the Deepest Nodes](https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(N), where N is the number of nodes of the
    // root.
    fun subtreeWithAllDeepest(root: TreeNode?): TreeNode? {
        if (root == null) {
            return root
        }

        val parent = hashMapOf<TreeNode, TreeNode>()
        val bfsDeque = ArrayDeque<TreeNode>()

        // The nodes, the leftmost node and the rightmost node of the
        // current level.
        bfsDeque.add(root)
        var first = root
        var last = root

        while (bfsDeque.isNotEmpty()) {
            first = bfsDeque.first()
            last = bfsDeque.last()
            repeat(bfsDeque.size) {
                val node = bfsDeque.removeFirst()

                node.left?.let {
                    parent[it] = node
                    bfsDeque.addLast(it)
                }
                node.right?.let {
                    parent[it] = node
                    bfsDeque.addLast(it)
                }
            }
        }

        while (first != last) {
            first = parent[first]
            last = parent[last]
        }

        return first
    }
}
