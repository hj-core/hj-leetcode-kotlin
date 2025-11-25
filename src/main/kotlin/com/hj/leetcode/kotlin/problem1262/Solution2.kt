package com.hj.leetcode.kotlin.problem1262

/**
 * LeetCode page: [1262. Greatest Sum Divisible by Three](https://leetcode.com/problems/greatest-sum-divisible-by-three/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maxSumDivThree(nums: IntArray): Int {
        // dp[r]@i:= the maximum sum from nums[..<i] such that it
        // modulo 3 equals r.
        var dp = intArrayOf(0, Int.MIN_VALUE, Int.MIN_VALUE)
        var oldDp = IntArray(3)

        for (num in nums) {
            oldDp = dp.also { dp = oldDp }
            val rem = num % 3
            for (r in 0..<3) {
                val r2 = (r + rem) % 3
                dp[r2] = maxOf(oldDp[r2], num + oldDp[r])
            }
        }
        return dp[0]
    }
}
