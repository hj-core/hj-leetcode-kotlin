package com.hj.leetcode.kotlin.problem1524

/**
 * LeetCode page: [1524. Number of Sub-arrays With Odd Sum](https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of `arr`.
    fun numOfSubarrays(arr: IntArray): Int {
        val mod = 1_000_000_007
        var oddPrefixCnt = 0
        var evenPrefixCnt = 1
        var isPrefixOdd = false
        var result = 0

        for (num in arr) {
            if (num and 1 == 1) {
                isPrefixOdd = !isPrefixOdd
            }
            if (isPrefixOdd) {
                result = (result + evenPrefixCnt) % mod
                oddPrefixCnt++
            } else {
                result = (result + oddPrefixCnt) % mod
                evenPrefixCnt++
            }
        }
        return result
    }
}
