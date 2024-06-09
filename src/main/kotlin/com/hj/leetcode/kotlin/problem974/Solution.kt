package com.hj.leetcode.kotlin.problem974

/**
 * LeetCode page: [974. Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/);
 */
class Solution {
    /* Complexity:
     * Time O(|nums|) and Space O(k);
     */
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        var prefixSum = 0
        val countPrefixSumRemainder = hashMapOf<Int, Int>()
        var countDivisible = 0

        countPrefixSumRemainder[0] = 1
        for (num in nums) {
            prefixSum += num
            val remainder = prefixSum.mod(k)
            val countRemainder = countPrefixSumRemainder[remainder] ?: 0
            countDivisible += countRemainder
            countPrefixSumRemainder[remainder] = countRemainder + 1
        }
        return countDivisible
    }
}