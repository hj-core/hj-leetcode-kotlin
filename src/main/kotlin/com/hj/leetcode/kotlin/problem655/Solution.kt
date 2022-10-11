package com.hj.leetcode.kotlin.problem655

import com.hj.leetcode.kotlin.common.model.TreeNode

/**
 * LeetCode page: [655. Print Binary Tree](https://leetcode.com/problems/print-binary-tree/);
 */
class Solution {

    private val strOfEmptyCell = ""

    /* Complexity:
     * Time O(H * 2^H) and Space O(H * 2^H) where H is the height of root;
     */
    fun printTree(root: TreeNode?): List<List<String>> {
        val height = root.height()
        val matrix = newMatrixContainer(height, strOfEmptyCell)

        if (root != null) updateMatrix(matrix, root)
        return matrix
    }

    private fun TreeNode?.height(): Int =
        if (this == null) 0 else 1 + maxOf(left.height(), right.height())

    private fun newMatrixContainer(treeHeight: Int, initialValue: String): List<MutableList<String>> {
        val totalColumn = (1 shl treeHeight) - 1
        return List(treeHeight) {
            MutableList(totalColumn) { initialValue }
        }
    }

    private fun updateMatrix(
        matrix: List<MutableList<String>>,
        node: TreeNode,
        rowOfNode: Int = 0,
        columnOfNode: Int = matrix[0].lastIndex shr 1
    ) {
        matrix[rowOfNode][columnOfNode] = node.`val`.toString()

        val rowOfChild = rowOfNode + 1

        node.left?.let {
            val columnOfLeft = columnOfNode - (1 shl (matrix.lastIndex - rowOfChild))
            updateMatrix(matrix, it, rowOfChild, columnOfLeft)
        }

        node.right?.let {
            val columnOfRight = columnOfNode + (1 shl (matrix.lastIndex - rowOfChild))
            updateMatrix(matrix, it, rowOfChild, columnOfRight)
        }
    }
}