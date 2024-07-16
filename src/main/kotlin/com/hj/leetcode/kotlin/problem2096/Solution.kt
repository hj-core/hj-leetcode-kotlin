package com.hj.leetcode.kotlin.problem2096

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [2096. Step-By-Step Directions From a Binary Tree Node to Another](https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in root;
     */
    fun getDirections(root: TreeNode?, startValue: Int, destValue: Int): String {
        val pathToStart = pathToTarget(root, startValue, mutableListOf())
        val pathToDestination = pathToTarget(root, destValue, mutableListOf())
        return getDirections(pathToStart, pathToDestination)
    }

    private fun pathToTarget(
        root: TreeNode?,
        targetValue: Int,
        currentPath: MutableList<TreeNode>,
    ): List<TreeNode> {
        if (root == null) {
            return emptyList()
        }

        currentPath.add(root)
        if (root.`val` == targetValue) {
            return currentPath.toList()
        }

        for (child in listOf(root.left, root.right)) {
            pathToTarget(child, targetValue, currentPath).let {
                if (it.isNotEmpty()) {
                    return it
                }
            }
        }
        currentPath.removeLast()
        return emptyList()
    }

    private fun getDirections(
        pathToStart: List<TreeNode>,
        pathToDestination: List<TreeNode>,
    ): String = buildString {
        val lcaIndex = indexOfLowestCommonAncestor(pathToStart, pathToDestination)
        // Directions from start to LCA
        for (index in pathToStart.lastIndex downTo (lcaIndex + 1)) {
            append('U')
        }
        // Directions from LCA to destination
        for (index in lcaIndex + 1..<pathToDestination.size) {
            if (pathToDestination[index] == pathToDestination[index - 1].left) {
                append('L')
            } else {
                append('R')
            }
        }
    }

    private fun indexOfLowestCommonAncestor(path1: List<TreeNode>, path2: List<TreeNode>): Int {
        require(path1[0] == path2[0])
        var result = 0
        while (result < path1.size
            && result < path2.size
            && path1[result] == path2[result]
        ) {
            result++
        }
        result--
        return result
    }
}