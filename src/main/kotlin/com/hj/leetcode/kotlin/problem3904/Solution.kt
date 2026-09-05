package com.hj.leetcode.kotlin.problem3904

/**
 * LeetCode page: [3904. Smallest Stable Index II](https://leetcode.com/problems/smallest-stable-index-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun firstStableIndex(
        nums: IntArray,
        k: Int,
    ): Int {
        var prefixMax = nums[0]
        var ans = 0
        var prefixMaxAtAns = nums[0]

        for ((i, num) in nums.withIndex()) {
            prefixMax = maxOf(prefixMax, num)
            if (prefixMaxAtAns - num > k) {
                ans = i + 1
                if (ans < nums.size) {
                    prefixMaxAtAns = maxOf(prefixMax, nums[ans])
                }
            }
        }

        return if (ans < nums.size) ans else -1
    }
}
