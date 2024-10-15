package com.hj.leetcode.kotlin.problem2938

/**
 * LeetCode page: [2938. Separate Black and White Balls](https://leetcode.com/problems/separate-black-and-white-balls/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s.
     */
    fun minimumSteps(s: String): Long {
        var result = 0L
        var moveTo = 0 // Index where the next zero should reside

        for ((i, c) in s.withIndex()) {
            if (c == '0') {
                result += i - moveTo
                moveTo += 1
            }
        }
        return result
    }
}
