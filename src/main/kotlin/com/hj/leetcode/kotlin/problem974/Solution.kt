package com.hj.leetcode.kotlin.problem974

/**
 * LeetCode page: [974. Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(k) where N is the length of nums;
     */
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        var result = 0
        var prefixSumModK = 0
        val countPrefixSumModKs = hashMapOf(0 to 1)

        for (num in nums) {
            prefixSumModK = (prefixSumModK + num).mod(k)
            val previousCount = countPrefixSumModKs[prefixSumModK] ?: 0
            result += previousCount
            countPrefixSumModKs[prefixSumModK] = previousCount + 1
        }
        return result
    }
}