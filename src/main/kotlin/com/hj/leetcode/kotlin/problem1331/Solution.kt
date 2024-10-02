package com.hj.leetcode.kotlin.problem1331

/**
 * LeetCode page: [1331. Rank Transform of an Array](https://leetcode.com/problems/rank-transform-of-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the length of arr.
     */
    fun arrayRankTransform(arr: IntArray): IntArray {
        if (arr.isEmpty()) {
            return intArrayOf()
        }
        val sortedIndices = arr.indices.sortedBy { arr[it] }
        val result = IntArray(arr.size)
        var prevNum = arr[sortedIndices[0]] - 1
        var rank = 0
        for (index in sortedIndices) {
            if (arr[index] != prevNum) {
                rank += 1
            }
            result[index] = rank
            prevNum = arr[index]
        }
        return result
    }
}
