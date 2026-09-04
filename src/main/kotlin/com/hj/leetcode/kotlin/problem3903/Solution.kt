package com.hj.leetcode.kotlin.problem3903

/**
 * LeetCode page: [3903. Smallest Stable Index I](https://leetcode.com/problems/smallest-stable-index-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun firstStableIndex(
        nums: IntArray,
        k: Int,
    ): Int {
        val n = nums.size
        val suffixMin = computeSuffixMin(nums)
        var prefixMax = nums[0]
        for (i in 0..<n) {
            prefixMax = maxOf(prefixMax, nums[i])
            if (prefixMax - suffixMin[i] <= k) {
                return i
            }
        }
        return -1
    }

    private fun computeSuffixMin(nums: IntArray): IntArray {
        val n = nums.size
        val suffixMin = IntArray(n)
        suffixMin[n - 1] = nums[n - 1]
        for (i in n - 2 downTo 0) {
            suffixMin[i] = minOf(suffixMin[i + 1], nums[i])
        }
        return suffixMin
    }
}
