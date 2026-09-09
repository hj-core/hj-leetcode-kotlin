package com.hj.leetcode.kotlin.problem3871

/**
 * LeetCode page: [3871. Count Commas in Range II](https://leetcode.com/problems/count-commas-in-range-ii/);
 */
class Solution {
    // Complexity:
    // Time O(Log n) and Space O(1).
    fun countCommas(n: Long): Long {
        var count = 0L
        var comma = 1000L
        while (n >= comma) {
            count += n - comma + 1
            comma *= 1000
        }
        return count
    }
}
