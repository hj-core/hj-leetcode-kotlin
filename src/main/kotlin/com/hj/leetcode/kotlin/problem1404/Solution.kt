package com.hj.leetcode.kotlin.problem1404

/**
 * LeetCode page: [1404. Number of Steps to Reduce a Number in Binary Representation to One](https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun numSteps(s: String): Int {
        require(s[0] == '1')
        var result = 0
        var carry = "0"

        for (i in s.lastIndex downTo 1) {
            when (s[i] + carry) {
                "00", "11" -> result += 1
                "01" -> result += 2
                else -> {
                    carry = "1"
                    result += 2
                }
            }
        }

        if (carry == "1") {
            result += 1
        }
        return result
    }
}