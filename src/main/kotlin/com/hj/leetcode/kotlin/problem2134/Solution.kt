package com.hj.leetcode.kotlin.problem2134

import kotlin.math.min

/**
 * LeetCode page: [2134. Minimum Swaps to Group All 1's Together II](https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun minSwaps(nums: IntArray): Int {
        val totalOnes = nums.count { it == 1 }
        var windowOnes = (0..<totalOnes).count { nums[it] == 1 } // window that starts at 0
        var result = totalOnes - windowOnes // required swaps for the window that starts at 0

        // Try all remaining possible window starts to find the minimum swaps
        for (windowStart in 1..<nums.size) {
            windowOnes -= nums[windowStart - 1]
            windowOnes += nums[(windowStart + totalOnes - 1) % nums.size]
            result = min(result, totalOnes - windowOnes)
        }
        return result
    }
}