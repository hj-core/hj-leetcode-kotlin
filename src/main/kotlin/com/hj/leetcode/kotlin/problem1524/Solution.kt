package com.hj.leetcode.kotlin.problem1524

/**
 * LeetCode page: [1524. Number of Sub-arrays With Odd Sum](https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of arr.
    fun numOfSubarrays(arr: IntArray): Int {
        val mod = 1_000_000_007
        // The parity of the current prefix sum and the count of previous prefix sums
        // by parity.
        var parity = 0
        val parityCount = intArrayOf(1, 0)
        var result = 0
        for (num in arr) {
            parity = (parity + num) and 1
            parityCount[parity]++
            result = (result + parityCount[parity xor 1]) % mod
        }
        return result
    }
}
