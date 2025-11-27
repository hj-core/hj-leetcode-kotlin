package com.hj.leetcode.kotlin.problem3381

/**
 * LeetCode page: [3381. Maximum Subarray Sum With Length Divisible by K](https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k/);
 */
class Solution {
    // Complexity:
    // Time O(N+k) and Space O(k) where N is the length of nums.
    fun maxSubarraySum(
        nums: IntArray,
        k: Int,
    ): Long {
        // minPrefixSum[r]:= the min prefix sum such that
        // (length - 1) % k equals r. The 1-shift is introduced
        // to eliminate the index-to-length conversion.
        val minPrefixSum = LongArray(k)
        var prefixSum = 0L
        for (i in 0..<k - 1) {
            prefixSum += nums[i]
            minPrefixSum[i] = prefixSum
        }

        var result = prefixSum + nums[k - 1]
        for (i in k - 1..<nums.size) {
            prefixSum += nums[i]
            val j = i % k
            result = maxOf(result, prefixSum - minPrefixSum[j])
            minPrefixSum[j] = minOf(minPrefixSum[j], prefixSum)
        }
        return result
    }
}
