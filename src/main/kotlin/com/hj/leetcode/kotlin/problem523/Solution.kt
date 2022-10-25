package com.hj.leetcode.kotlin.problem523

/**
 * LeetCode page: [523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(K) where N is the size of nums and k equals k;
     */
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        /* deductions contain prefixSumModK until(exclusive) index before the current Index, they are used to
         * query the SubArraySumModK ended in the current index;
         */
        val deductions = hashSetOf<Int>().also { it.add(0) }
        var prevPrefixSumModK = nums[0] % k

        for (index in 1..nums.lastIndex) {
            val currPrefixSumModK = (prevPrefixSumModK + nums[index]) % k

            val hasSubArraySumModKIsZero = currPrefixSumModK in deductions
            if (hasSubArraySumModKIsZero) return true

            deductions.add(prevPrefixSumModK)
            prevPrefixSumModK = currPrefixSumModK
        }
        return false
    }
}