package com.hj.leetcode.kotlin.problem1590

import kotlin.math.min

/**
 * LeetCode page: [1590. Make Sum Divisible by P](https://leetcode.com/problems/make-sum-divisible-by-p/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums.
     */
    fun minSubarray(
        nums: IntArray,
        p: Int,
    ): Int {
        val prefixModSums = IntArray(nums.size)
        prefixModSums[0] = nums[0] % p
        for (i in 1..<nums.size) {
            prefixModSums[i] = (nums[i] + prefixModSums[i - 1]) % p
        }

        val target = prefixModSums.last()
        if (target == 0) {
            return 0
        }

        var result = nums.size
        val latestPrefix = mutableMapOf(0 to -1) // The latest index of prefix sum mod p
        for ((i, modSum) in prefixModSums.withIndex()) {
            val complement = (modSum - target + p) % p
            if (complement in latestPrefix) {
                result = min(result, i - checkNotNull(latestPrefix[complement]))
            }
            latestPrefix[modSum] = i
        }
        return if (result < nums.size) result else -1
    }
}
