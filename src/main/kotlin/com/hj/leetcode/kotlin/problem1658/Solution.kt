package com.hj.leetcode.kotlin.problem1658

/**
 * LeetCode page: [1658. Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun minOperations(
        nums: IntArray,
        x: Int,
    ): Int {
        var maxPrefixLen = 0
        var prefixSum = 0
        while (maxPrefixLen < nums.size && prefixSum < x) {
            prefixSum += nums[maxPrefixLen]
            maxPrefixLen++
        }

        if (maxPrefixLen == nums.size) {
            return if (prefixSum == x) maxPrefixLen else -1
        }

        var minOps = nums.size + 1
        var suffixLen = 0
        var suffixSum = 0
        prefixSum += nums[maxPrefixLen]
        for (prefixLen in maxPrefixLen downTo 0) {
            prefixSum -= nums[prefixLen]
            while (prefixSum + suffixSum < x) {
                suffixLen++
                suffixSum += nums[nums.size - suffixLen]
            }

            if (prefixSum + suffixSum == x) {
                minOps = minOf(minOps, prefixLen + suffixLen)
            }
        }

        return if (minOps <= nums.size) minOps else -1
    }
}
