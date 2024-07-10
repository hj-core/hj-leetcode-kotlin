package com.hj.leetcode.kotlin.problem1598

/**
 * LeetCode page: [1598. Crawler Log Folder](https://leetcode.com/problems/crawler-log-folder/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of logs;
     */
    fun minOperations(logs: Array<String>): Int {
        var result = 0
        for (log in logs) {
            /* Assume that any log other than "./" and "../" represents a valid
             * move folder operation.
             */
            when (log) {
                "./" -> continue
                "../" -> result = (result - 1).coerceAtLeast(0)
                else -> result += 1
            }
        }
        return result
    }
}