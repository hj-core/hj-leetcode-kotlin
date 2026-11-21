package com.hj.leetcode.kotlin.problem3041

/**
 * LeetCode page: [3041. Maximize Consecutive Elements in an Array After Modification](https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums.
    fun maxSelectedElements(nums: IntArray): Int {
        val nums = nums.sortedArray()
        val n = nums.size
        // maximum number of selected elements whose minimum values are nums[i] and nums[i]+1
        val dp = intArrayOf(1, 1)
        var maxSelected = 1
        for (i in n - 2 downTo 0) {
            when (nums[i + 1] - nums[i]) {
                0 -> {
                    if (i < n - 2 && nums[i + 2] == nums[i]) {
                        continue
                    }
                    dp[0] = 1 + dp[1]
                }

                1 -> {
                    dp[1]++
                    dp[0]++
                }

                2 -> {
                    dp[1] = 1 + dp[0]
                    dp[0] = 1
                }

                else -> {
                    dp[0] = 1
                    dp[1] = 1
                }
            }

            maxSelected = maxOf(maxSelected, dp[0], dp[1])
        }

        return maxSelected
    }
}
