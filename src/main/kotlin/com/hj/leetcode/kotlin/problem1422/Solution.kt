package com.hj.leetcode.kotlin.problem1422

import kotlin.math.max

/**
 * LeetCode page: [1422. Maximum Score After Splitting a String](https://leetcode.com/problems/maximum-score-after-splitting-a-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun maxScore(s: String): Int {
        var leftZeros = 0
        var rightOnes = s.count { it == '1' }
        var result = -1

        for (leftLength in 1..<s.length) {
            if (s[leftLength - 1] == '0') {
                leftZeros++
            } else {
                rightOnes--
            }
            result = max(result, leftZeros + rightOnes)
        }
        return result
    }
}
