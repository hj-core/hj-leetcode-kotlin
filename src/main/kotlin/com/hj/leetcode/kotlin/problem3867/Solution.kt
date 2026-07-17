package com.hj.leetcode.kotlin.problem3867

/**
 * LeetCode page: [3867. Sum of GCD of Formed Pairs](https://leetcode.com/problems/sum-of-gcd-of-formed-pairs/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN+NLogM) and Space O(N) where N is the length of nums
    // and M is the maximum value in nums.
    fun gcdSum(nums: IntArray): Long {
        val n = nums.size
        var mx = nums[0]
        val prefixGcd = IntArray(n)
        for (i in nums.indices) {
            mx = maxOf(mx, nums[i])
            prefixGcd[i] = gcd(nums[i], mx)
        }

        prefixGcd.sort()
        var gcdSum = 0L
        for (i in 0..<(n / 2)) {
            gcdSum += gcd(prefixGcd[i], prefixGcd[n - 1 - i])
        }

        return gcdSum
    }

    private tailrec fun gcd(
        a: Int,
        b: Int,
    ): Int {
        if (b == 0) {
            return a
        }
        return gcd(b, a % b)
    }
}
