package com.hj.leetcode.kotlin.problem1424

/**
 * LeetCode page: [1424. Diagonal Traverse II](https://leetcode.com/problems/diagonal-traverse-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the total number of elements in nums;
     */
    fun findDiagonalOrder(nums: List<List<Int>>): IntArray {
        val result = IntArray(nums.sumOf { it.size })
        var index = 0
        val queue = ArrayDeque<MatrixIndex>()

        queue.addLast(MatrixIndex(0, 0))
        while (queue.isNotEmpty()) {
            val matrixIndex = queue.removeFirst()
            val element = nums.getOrNull(matrixIndex) ?: continue
            result[index] = element
            index++

            with(matrixIndex) {
                if (column == 0) {
                    queue.addLast(MatrixIndex(row + 1, 0))
                }
                queue.addLast(MatrixIndex(row, column + 1))
            }
        }
        return result
    }

    private data class MatrixIndex(val row: Int, val column: Int)

    private fun List<List<Int>>.getOrNull(index: MatrixIndex): Int? {
        return this.getOrNull(index.row)?.getOrNull(index.column)
    }
}