package com.hj.leetcode.kotlin.problem2169

/**
 * LeetCode page: [2169. Count Operations to Obtain Zero](https://leetcode.com/problems/count-operations-to-obtain-zero/);
 */
class Solution {
    // Complexity:
    // Time O(Log(num1+num2)) and Space O(1)
    fun countOperations(
        num1: Int,
        num2: Int,
    ): Int {
        var result = 0
        var a = num1
        var b = num2
        while (a != 0) {
            result += b / a
            a = b % a.also { b = a }
        }
        return result
    }
}
