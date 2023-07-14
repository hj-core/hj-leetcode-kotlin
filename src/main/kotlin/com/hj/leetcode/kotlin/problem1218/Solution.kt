package com.hj.leetcode.kotlin.problem1218

/**
 * LeetCode page: [1218. Longest Arithmetic Subsequence of Given Difference](https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of arr;
     */
    fun longestSubsequence(arr: IntArray, difference: Int): Int {
        val longestLength = hashMapOf<Int, Int>() // entry = (end value, length)
        for (value in arr) {
            longestLength[value] = 1 + (longestLength[value - difference] ?: 0)
        }
        return longestLength.values.max()!!
    }
}