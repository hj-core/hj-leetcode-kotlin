package com.hj.leetcode.kotlin.problem931

/**
 * LeetCode page: [931. Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/description/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M and N are the number of rows and columns of matrix;
     */
    fun minFallingPathSum(matrix: Array<IntArray>): Int {
        val minSumsCurrLevel = matrix[0].clone()
        val minSumsReachTarget = ArrayDeque<Int>()
        repeat(matrix.size - 1) { currLevel ->
            val nextLevel = currLevel + 1
            minSumsReachTarget.apply {
                addLast(minSumsCurrLevel[0])
                addLast(minSumsCurrLevel[0])
            }
            for (column in minSumsCurrLevel.indices) {
                minSumsCurrLevel
                    .getOrNull(column + 1)
                    ?.let { minSumsReachTarget.addLast(it) }
                minSumsCurrLevel[column] = matrix[nextLevel][column] + minSumsReachTarget.min()!!
                minSumsReachTarget.removeFirst()
            }
            minSumsReachTarget.removeLast()
        }
        return minSumsCurrLevel.min()!!
    }
}