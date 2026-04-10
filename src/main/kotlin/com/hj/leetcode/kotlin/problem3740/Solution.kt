package com.hj.leetcode.kotlin.problem3740

/**
 * LeetCode page: [3740. Minimum Distance Between Three Equal Elements I](https://leetcode.com/problems/minimum-distance-between-three-equal-elements-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun minimumDistance(nums: IntArray): Int {
        val indexesByNum = nums.indices.groupBy { nums[it] }

        var minHalfDist = nums.size
        for ((_, indexes) in indexesByNum) {
            for (j in 2..<indexes.size) {
                minHalfDist = minOf(minHalfDist, indexes[j] - indexes[j - 2])
            }
        }

        return if (minHalfDist == nums.size) -1 else minHalfDist * 2
    }
}
