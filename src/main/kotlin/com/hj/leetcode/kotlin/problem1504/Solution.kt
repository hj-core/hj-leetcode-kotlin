package com.hj.leetcode.kotlin.problem1504

/**
 * LeetCode page: [1504. Count Submatrices With All Ones](https://leetcode.com/problems/count-submatrices-with-all-ones/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(N) where M and N are the number
    // of rows and columns of mat, respectively.
    fun numSubmat(mat: Array<IntArray>): Int {
        // heights[c]@r:= the height of one-pillar grounding
        // at cell(r, c).
        val heights = IntArray(mat[0].size)
        val monoStack =
            Array(mat[0].size + 1) {
                intArrayOf(0, 0) // (height, count) pairs
            }
        var result = 0

        for (r in mat.indices) {
            var stackTop = 0
            var stackSum = 0 // sum(height*count)

            for (c in mat[r].indices) {
                if (mat[r][c] == 0) {
                    heights[c] = 0
                    stackTop = 0
                    stackSum = 0
                } else {
                    heights[c]++
                    var count = 1
                    while (heights[c] <= monoStack[stackTop][0]) {
                        count += monoStack[stackTop][1]
                        stackSum -= monoStack[stackTop][0] * monoStack[stackTop][1]
                        stackTop--
                    }

                    stackTop++
                    monoStack[stackTop][0] = heights[c]
                    monoStack[stackTop][1] = count
                    stackSum += heights[c] * count
                    result += stackSum
                }
            }
        }
        return result
    }
}
