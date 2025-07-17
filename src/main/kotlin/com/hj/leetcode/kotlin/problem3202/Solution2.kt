package com.hj.leetcode.kotlin.problem3202

/**
 * LeetCode page: [3202. Find the Maximum Length of Valid Subsequence II](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/);
 */
class Solution2 {
    // Complexity:
    // Time O(kN+k^2) and Space O(k^2) where N is the length
    // of nums.
    fun maximumLength(
        nums: IntArray,
        k: Int,
    ): Int {
        // dp[i][j]:= the longest length of a sequence with a
        // remainder matching the alternating i-j pattern.
        val dp = Array(k) { IntArray(k) }

        var result = 0
        for (num in nums) {
            val r = num % k
            for (i in 0..<k) {
                dp[i][r] = dp[r][i] + 1
                result = maxOf(result, dp[i][r])
            }
        }
        return result
    }
}
