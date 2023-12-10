package com.hj.leetcode.kotlin.problem867

/**
 * LeetCode page: [867. Transpose Matrix](https://leetcode.com/problems/transpose-matrix/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the number of
     * rows and columns of matrix;
     */
    fun transpose(matrix: Array<IntArray>): Array<IntArray> {
        return Array(matrix[0].size) { row ->
            IntArray(matrix.size) { column ->
                matrix[column][row]
            }
        }
    }
}