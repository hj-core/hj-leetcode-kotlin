package com.hj.leetcode.kotlin.problem1331

/**
 * LeetCode page: [1331. Rank Transform of an Array](https://leetcode.com/problems/rank-transform-of-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of arr.
    fun arrayRankTransform(arr: IntArray): IntArray {
        if (arr.isEmpty()) {
            return intArrayOf()
        }

        val indices = arr.indices.sortedBy { arr[it] }
        val rank = IntArray(arr.size)
        rank[indices[0]] = 1
        for (i in 1..<indices.size) {
            rank[indices[i]] =
                rank[indices[i - 1]] + (if (arr[indices[i]] == arr[indices[i - 1]]) 0 else 1)
        }

        return rank
    }
}
