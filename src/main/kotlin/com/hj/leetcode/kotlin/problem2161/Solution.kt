package com.hj.leetcode.kotlin.problem2161

/**
 * LeetCode page: [2161. Partition Array According to Given Pivot](https://leetcode.com/problems/partition-array-according-to-given-pivot/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun pivotArray(
        nums: IntArray,
        pivot: Int,
    ): IntArray {
        val result = IntArray(nums.size) { pivot }
        var i = 0
        var j = nums.size - nums.count { it > pivot }
        for (num in nums) {
            when {
                num < pivot -> result[i++] = num
                num > pivot -> result[j++] = num
            }
        }

        return result
    }
}
