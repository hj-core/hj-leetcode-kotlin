package com.hj.leetcode.kotlin.problem1524

/**
 * LeetCode page: [1524. Number of Sub-arrays With Odd Sum](https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of `arr`.
    fun numOfSubarrays(arr: IntArray): Int {
        val mod = 1_000_000_007
        var oddSumPrefixCnt = 0
        var evenSumPrefixCnt = 1
        var isPrefixSumOdd = false

        for (num in arr) {
            if (num and 1 == 1) {
                isPrefixSumOdd = !isPrefixSumOdd
            }
            if (isPrefixSumOdd) {
                oddSumPrefixCnt++
            } else {
                evenSumPrefixCnt++
            }
        }
        return ((oddSumPrefixCnt.toLong() * evenSumPrefixCnt) % mod).toInt()
    }
}
