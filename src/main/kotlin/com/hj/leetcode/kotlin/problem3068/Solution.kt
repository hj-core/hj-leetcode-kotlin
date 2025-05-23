package com.hj.leetcode.kotlin.problem3068

/**
 * LeetCode page: [3068. Find the Maximum Sum of Node Values](https://leetcode.com/problems/find-the-maximum-sum-of-node-values/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maximumValueSum(
        nums: IntArray,
        k: Int,
        edges: Array<IntArray>,
    ): Long {
        var result = 0L
        var incCnt = 0 // Count the numbers for which xor is beneficial
        var minInc = k
        var minDec = k

        for (num in nums) {
            val xored = num xor k
            val diff = xored - num // diff can't be zero

            if (diff > 0) {
                result += xored
                incCnt++
                minInc = minOf(minInc, diff)
            } else {
                result += num
                minDec = minOf(minDec, -diff)
            }
        }

        if (incCnt and 1 != 0) {
            result -= minOf(minInc, minDec)
        }
        return result
    }
}
