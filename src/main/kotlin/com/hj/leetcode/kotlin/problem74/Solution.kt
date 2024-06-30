package com.hj.leetcode.kotlin.problem74

/**
 * LeetCode page: [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(LogM+LogN) and Space O(1) where M and N are the number of rows and columns in matrix;
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val row = binarySearch(target, 0, matrix.size) { index ->
            matrix[index].last()
        }.let { if (it < 0) -(it + 1) else it }

        return (row in matrix.indices) && (matrix[row].binarySearch(target) >= 0)
    }

    private fun <T : Comparable<T>> binarySearch(
        target: T,
        fromIndex: Int,
        untilIndex: Int,
        readValue: (index: Int) -> T
    ): Int {
        var low = fromIndex
        var high = untilIndex - 1
        while (low <= high) {
            val mid = (low + high).ushr(1)
            val midValue = readValue(mid)

            when {
                midValue < target -> low = mid + 1
                midValue > target -> high = mid - 1
                else -> return mid // target found and return its index
            }
        }
        return -(low + 1) // target is not found and return the -(insertion point + 1)
    }
}