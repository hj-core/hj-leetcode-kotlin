package com.hj.leetcode.kotlin.problem3411

/**
 * LeetCode page: [3411. Maximum Subarray With Equal Products](https://leetcode.com/problems/maximum-subarray-with-equal-products/);
 */
class Solution {
    // Complexity:
    // Time O(NM) and Space O(M^2) where N is the length of nums and
    // M is the maximum possible value in nums.
    fun maxLength(nums: IntArray): Int {
        // Transform each num in nums to its prime factorization. An `Equal Products`
        // subarray of length greater than two requires the second-largest value
        // in each prime column to be 0, i.e., at most one positive number in each
        // prime column.
        val maxValue = 10
        val primes = intArrayOf(2, 3, 5, 7)
        val hasFactor =
            Array(maxValue + 1) { num ->
                BooleanArray(primes.size) { k ->
                    num % primes[k] == 0
                }
            }

        var maxLen = 2
        val lastIndex = IntArray(primes.size) { -1 }
        var left = -1 // valid subarray nums[left+1..=right]
        for (right in nums.indices) {
            val num = nums[right]
            for (k in primes.indices) {
                if (hasFactor[num][k]) {
                    left = maxOf(left, lastIndex[k])
                    lastIndex[k] = right
                }
            }
            maxLen = maxOf(maxLen, right - left)
        }
        return maxLen
    }
}
