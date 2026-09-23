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
        var shortage = x

        var maxPrefixLen = 0
        while (maxPrefixLen < nums.size && 0 < shortage) {
            shortage -= nums[maxPrefixLen]
            maxPrefixLen++
        }

        if (maxPrefixLen == nums.size) {
            return if (shortage == 0) maxPrefixLen else -1
        }

        var minOps = if (shortage == 0) maxPrefixLen else nums.size + 1
        var suffixLen = 0
        for (prefixLen in maxPrefixLen - 1 downTo 0) {
            shortage += nums[prefixLen]
            while (shortage > 0) {
                suffixLen++
                shortage -= nums[nums.size - suffixLen]
            }

            if (shortage == 0) {
                minOps = minOf(minOps, prefixLen + suffixLen)
            }
        }

        return if (minOps <= nums.size) minOps else -1
    }
}
