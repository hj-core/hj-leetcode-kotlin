package com.hj.leetcode.kotlin.problem2661

/**
 * LeetCode page: [2661. First Completely Painted Row or Column](https://leetcode.com/problems/first-completely-painted-row-or-column/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N)
     * where N is the length of arr and the total number of elements in mat.
     */
    fun firstCompleteIndex(
        arr: IntArray,
        mat: Array<IntArray>,
    ): Int {
        val indicesByVal = IntArray(arr.size + 1)
        for ((i, num) in arr.withIndex()) {
            indicesByVal[num] = i
        }

        var result = arr.lastIndex
        // Check the max index of each row
        for (row in mat.indices) {
            var maxIndex = 0
            for (col in mat[row].indices) {
                val num = mat[row][col]
                maxIndex = maxOf(maxIndex, indicesByVal[num])
            }
            result = minOf(result, maxIndex)
        }
        // Check the max index of each column
        for (col in mat[0].indices) {
            var maxIndex = 0
            for (row in mat.indices) {
                val num = mat[row][col]
                maxIndex = maxOf(maxIndex, indicesByVal[num])
            }
            result = minOf(result, maxIndex)
        }
        return result
    }
}
