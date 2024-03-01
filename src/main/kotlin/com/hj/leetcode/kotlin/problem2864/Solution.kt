package com.hj.leetcode.kotlin.problem2864

/**
 * LeetCode page: [2864. Maximum Odd Binary Number](https://leetcode.com/problems/maximum-odd-binary-number/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun maximumOddBinaryNumber(s: String): String {
        val oneCount = s.count { it == '1' }
        check(oneCount >= 1)

        return StringBuilder(s.length).apply {
            repeat(oneCount - 1) { append('1') }
            repeat(s.length - oneCount) { append('0') }
            append('1')
        }.toString()
    }
}