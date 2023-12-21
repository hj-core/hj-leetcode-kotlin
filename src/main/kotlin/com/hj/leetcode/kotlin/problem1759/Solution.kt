package com.hj.leetcode.kotlin.problem1759

/**
 * LeetCode page: [1759. Count Number of Homogenous Substrings](https://leetcode.com/problems/count-number-of-homogenous-substrings/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun countHomogenous(s: String): Int {
        val modulo = 1_000_000_007
        var result = 1
        var substringLength = 1

        for (index in 1..<s.length) {
            if (s[index] == s[index - 1]) {
                substringLength++
            } else {
                substringLength = 1
            }
            result = (result + substringLength) % modulo
        }
        return result
    }
}