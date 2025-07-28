package com.hj.leetcode.kotlin.problem2044

/**
 * LeetCode page: [2044. Count Number of Maximum Bitwise-OR Subsets](https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NM) and Space O(M) where N is the size of
     * nums and M is the maximum or value.
     */
    fun countMaxOrSubsets(nums: IntArray): Int {
        val maxOr = nums.reduce(Int::or)

        // dp[v]@i := the number of subsets of nums[..<i]
        // whose subset XOR equals v.
        val dp = IntArray(maxOr + 1)
        dp[0] = 1

        for (num in nums) {
            for (v in maxOr downTo 0) {
                if (dp[v] > 0) {
                    dp[v or num] += dp[v]
                }
            }
        }
        return dp[maxOr]
    }
}
