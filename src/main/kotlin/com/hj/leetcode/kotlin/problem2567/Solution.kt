package com.hj.leetcode.kotlin.problem2567

/**
 * LeetCode page: [2567. Minimum Score by Changing Two Elements](https://leetcode.com/problems/minimum-score-by-changing-two-elements/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minimizeSum(nums: IntArray): Int {
        if (nums.size < 4) {
            return 0
        }

        val minThree = IntArray(4) { nums[it] }
        minThree.sort()
        val maxThree = IntArray(4) { nums[it] }
        maxThree.sort()

        for (i in 4..<nums.size) {
            minThree[3] = nums[i]
            minThree.sort()
            maxThree[0] = nums[i]
            maxThree.sort()
        }

        return minOf(
            maxThree[3] - minThree[2],
            maxThree[2] - minThree[1],
            maxThree[1] - minThree[0],
        )
    }
}
