package com.hj.leetcode.kotlin.problem1513

/**
 * LeetCode page: [1513. Number of Substrings With Only 1s](https://leetcode.com/problems/number-of-substrings-with-only-1s/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun numSub(s: String): Int {
        var result = 0L
        var oneCnt = 0

        for (c in s) {
            if (c == '1') {
                oneCnt++
                result += oneCnt
            } else {
                oneCnt = 0
            }
        }
        return (result % 1_000_000_007).toInt()
    }
}
