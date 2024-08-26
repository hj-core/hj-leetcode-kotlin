package com.hj.leetcode.kotlin.problem590

import com.hj.leetcode.kotlin.common.model.Node

/**
 * LeetCode page: [590. N-ary Tree Postorder Traversal](https://leetcode.com/problems/n-ary-tree-postorder-traversal/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(H) where N is the number of nodes in root
     * and H is the height of root;
     */
    fun postorder(root: Node?): List<Int> =
        buildList {
            postorder(root) { node -> add(node.`val`) }
        }

    private fun postorder(
        root: Node?,
        onEachNode: (node: Node) -> Unit,
    ) {
        if (root == null) {
            return
        }

        for (node in root.children) {
            postorder(node, onEachNode)
        }
        onEachNode(root)
    }
}
