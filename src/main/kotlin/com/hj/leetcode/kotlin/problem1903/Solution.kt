package com.hj.leetcode.kotlin.problem1903

/**
 * LeetCode page: [1903. Largest Odd Number in String](https://leetcode.com/problems/largest-odd-number-in-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of num;
     */
    fun largestOddNumber(num: String): String {
        return num.dropLastWhile { it in "02468" }
    }
}