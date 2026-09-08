package com.hj.leetcode.kotlin.problem3870

/**
 * LeetCode page: [3870. Count Commas in Range](https://leetcode.com/problems/count-commas-in-range/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(1).
    fun countCommas(n: Int): Int {
        var count = 0
        var comma = 1000
        while (comma <= n) {
            count += n - comma + 1
            comma *= 1000
        }
        return count
    }
}
