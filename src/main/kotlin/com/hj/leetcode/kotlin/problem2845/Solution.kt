package com.hj.leetcode.kotlin.problem2845

/**
 * LeetCode page: [2845. Count of Interesting Subarrays](https://leetcode.com/problems/count-of-interesting-subarrays/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(min(N,modulo)) where N is the length of nums.
    fun countInterestingSubarrays(
        nums: List<Int>,
        modulo: Int,
        k: Int,
    ): Long {
        // We consider the number of indices where value % modulo equals k for
        // prefix arrays, and then further take the count modulo. Then, the problem
        // is similar to finding subarrays whose sum equal a specific value.
        var prefixCnt = 0
        val cntFreq = mutableMapOf<Int, Int>()
        var result = 0L

        cntFreq[0] = 1
        for (num in nums) {
            if (num % modulo == k) {
                prefixCnt = (prefixCnt + 1) % modulo
            }
            val counterPart =
                prefixCnt.let {
                    if (it >= k) it - k else it - k + modulo
                }
            result += (cntFreq[counterPart] ?: 0).toLong()
            cntFreq[prefixCnt] = (cntFreq[prefixCnt] ?: 0) + 1
        }
        return result
    }
}
