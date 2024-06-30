package com.hj.leetcode.kotlin.problem344

/**
 * LeetCode page: [344. Reverse String](https://leetcode.com/problems/reverse-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of s;
     */
    fun reverseString(s: CharArray): Unit {
        var left = 0
        var right = s.lastIndex
        while (left < right) {
            s[left] = s[right].also { s[right] = s[left] }
            left++
            right--
        }
    }
}