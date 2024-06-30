package com.hj.leetcode.kotlin.problem523

/**
 * LeetCode page: [523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(k) where N is the size of nums;
     */
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        // Tracking the prefixSumModKs with one index delay to ensure the subarray size
        val prefixSumModKs = mutableSetOf<Int>()
        var prevPrefixSumModK = 0

        for (num in nums) {
            val prefixSumModK = (prevPrefixSumModK + num) % k
            if (prefixSumModK in prefixSumModKs) {
                return true
            }

            prefixSumModKs.add(prevPrefixSumModK)
            prevPrefixSumModK = prefixSumModK
        }
        return false
    }
}