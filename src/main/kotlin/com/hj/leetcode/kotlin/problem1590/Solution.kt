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
        val sum = nums.fold(0L) { acc, i -> acc + i }
        val target = (sum % p).toInt()
        if (target == 0) {
            return 0
        }

        var result = nums.size
        var prefixModSum = 0
        val latestPrefix = mutableMapOf(0 to -1) // The latest index of prefix sum mod p
        for (i in nums.indices) {
            prefixModSum = (prefixModSum + nums[i]) % p
            val complement = (prefixModSum - target + p) % p
            if (complement in latestPrefix) {
                result = min(result, i - checkNotNull(latestPrefix[complement]))
            }
            latestPrefix[prefixModSum] = i
        }
        return if (result < nums.size) result else -1
    }
}
