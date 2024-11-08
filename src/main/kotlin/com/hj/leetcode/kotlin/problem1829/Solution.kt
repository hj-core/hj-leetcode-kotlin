package com.hj.leetcode.kotlin.problem1829

/**
 * LeetCode page: [1829. Maximum XOR for Each Query](https://leetcode.com/problems/maximum-xor-for-each-query/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the length of nums.
     */
    fun getMaximumXor(
        nums: IntArray,
        maximumBit: Int,
    ): IntArray {
        // require(maximumBit < 31)
        // require(nums.all { it < 1 shl maximumBit })
        val maxXor = (1 shl maximumBit) - 1
        var prefixXor = 0
        val result = IntArray(nums.size)
        for (i in result.indices.reversed()) {
            prefixXor = prefixXor xor nums[nums.lastIndex - i]
            result[i] = prefixXor xor maxXor
        }
        return result
    }
}
