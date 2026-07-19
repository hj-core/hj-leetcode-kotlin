package com.hj.leetcode.kotlin.problem1979

/**
 * LeetCode page: [1979. Find Greatest Common Divisor of Array](https://leetcode.com/problems/find-greatest-common-divisor-of-array/);
 */
class Solution {
    // Complexity:
    // Time O(N+LogM) and Space O(1) where N is the length of nums and
    // M is the maximum number in nums.
    fun findGCD(nums: IntArray): Int = gcd(nums.min(), nums.max())

    private tailrec fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
