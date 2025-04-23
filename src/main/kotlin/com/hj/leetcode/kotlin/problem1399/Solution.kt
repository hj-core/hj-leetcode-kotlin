package com.hj.leetcode.kotlin.problem1399

/**
 * LeetCode page: [1399. Count Largest Group](https://leetcode.com/problems/count-largest-group/);
 */
class Solution {
    // Complexity:
    // Time O(nLog(n)) and Space O(log(n)).
    fun countLargestGroup(n: Int): Int {
        val sumFreq = (1..n).groupingBy(::computeDigitSum).eachCount()
        val maxFreq = sumFreq.values.max()
        return sumFreq.count { it.value == maxFreq }
    }

    private fun computeDigitSum(x: Int): Int {
        var result = 0
        var curr = x
        while (curr > 0) {
            result += curr % 10
            curr /= 10
        }
        return result
    }
}
