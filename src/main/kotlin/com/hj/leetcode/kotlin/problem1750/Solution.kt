package com.hj.leetcode.kotlin.problem1750

/**
 * LeetCode page: [1750. Minimum Length of String After Deleting Similar Ends](https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun minimumLength(s: String): Int {
        var left = 0
        var right = s.length - 1

        while (left < right && s[left] == s[right]) {
            val c = s[left]

            while (left <= right && s[left] == c) {
                left++
            }
            while (left < right && s[right] == c) {
                right--
            }
        }
        return right - left + 1
    }
}