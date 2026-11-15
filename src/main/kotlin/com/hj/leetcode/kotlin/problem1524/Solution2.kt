package com.hj.leetcode.kotlin.problem1524

/**
 * LeetCode page: [1524. Number of Sub-arrays With Odd Sum](https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of arr.
    fun numOfSubarrays(arr: IntArray): Int {
        val mod = 1_000_000_007
        var prefixSum = 0
        var oddPrefixSums = 0
        for (num in arr) {
            prefixSum += num
            oddPrefixSums += prefixSum and 1
        }
        val evenPrefixSums = 1L + arr.size - oddPrefixSums
        return (oddPrefixSums * evenPrefixSums).mod(mod)
    }
}
