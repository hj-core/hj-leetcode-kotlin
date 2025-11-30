package com.hj.leetcode.kotlin.problem1590

/**
 * LeetCode page: [1590. Make Sum Divisible by P](https://leetcode.com/problems/make-sum-divisible-by-p/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the size of nums.
    fun minSubarray(
        nums: IntArray,
        p: Int,
    ): Int {
        val arraySum = nums.sumOf(Int::toLong)
        val subarrayRem = (arraySum % p).toInt()
        if (subarrayRem == 0) {
            return 0
        }

        // maxPrefix[rem]:=
        // the max prefix such that prefix sum % p equals rem.
        val maxPrefix = hashMapOf(0 to -1)
        var prefixRem = 0
        var result = nums.size

        for (i in nums.indices) {
            prefixRem = (prefixRem + nums[i]) % p
            val complement = (prefixRem - subarrayRem).mod(p)

            maxPrefix[complement]?.let {
                result = minOf(result, i - it)
            }
            maxPrefix[prefixRem] = i
        }
        return if (result < nums.size) result else -1
    }
}
