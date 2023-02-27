package com.hj.leetcode.kotlin.problem427

/**
 * LeetCode page: [427. Construct Quad Tree](https://leetcode.com/problems/construct-quad-tree/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(LogN) where N is the size of grid;
     */
    fun construct(grid: Array<IntArray>): Node? {
        return if (grid.isEmpty()) null else constructSubGrid(grid)
    }

    private fun constructSubGrid(
        grid: Array<IntArray>,
        rowStart: Int = 0,
        columnStart: Int = 0,
        size: Int = grid.size
    ): Node {

        require(size > 0)
        require(size == 1 || size and 1 == 0)

        val isSingleElement = size == 1
        if (isSingleElement) {
            val value = grid[rowStart][columnStart] == 1
            return Node(value, true)
        }

        val halfSize = size shr 1
        val secondHalfRowStart = rowStart + halfSize
        val secondHalfColumnStart = columnStart + halfSize

        val topLeft = constructSubGrid(grid, rowStart, columnStart, halfSize)
        val topRight = constructSubGrid(grid, rowStart, secondHalfColumnStart, halfSize)
        val bottomLeft = constructSubGrid(grid, secondHalfRowStart, columnStart, halfSize)
        val bottomRight = constructSubGrid(grid, secondHalfRowStart, secondHalfColumnStart, halfSize)

        return if (shouldMergeAsLeaf(topLeft, topRight, bottomLeft, bottomRight)) {
            Node(topLeft.`val`, true)
        } else {
            Node(topLeft.`val`, false).apply {
                this.topLeft = topLeft
                this.topRight = topRight
                this.bottomLeft = bottomLeft
                this.bottomRight = bottomRight
            }
        }
    }

    private fun shouldMergeAsLeaf(topLeft: Node, topRight: Node, bottomLeft: Node, bottomRight: Node): Boolean {
        val isChildNodesAllLeaf =
            topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
        if (!isChildNodesAllLeaf) return false

        return topLeft.`val` == topRight.`val` &&
                topLeft.`val` == bottomLeft.`val` &&
                topLeft.`val` == bottomRight.`val`
    }
}

class Node(var `val`: Boolean, var isLeaf: Boolean) {
    var topLeft: Node? = null
    var topRight: Node? = null
    var bottomLeft: Node? = null
    var bottomRight: Node? = null
}