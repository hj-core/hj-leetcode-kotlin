package com.hj.leetcode.kotlin.problem1422

import kotlin.math.max

/**
 * LeetCode page: [1422. Maximum Score After Splitting a String](https://leetcode.com/problems/maximum-score-after-splitting-a-string/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun maxScore(s: String): Int {
        var totalZeros = 0
        var leftZeros = 0
        var shiftedResult = 0

        for (leftLength in 1..<s.length) {
            if (s[leftLength - 1] == '0') {
                totalZeros++
                leftZeros++
            }
            val shiftedScore = leftZeros * 2 + s.length - leftLength
            shiftedResult = max(shiftedResult, shiftedScore)
        }
        totalZeros += if (s.last() == '0') 1 else 0
        return shiftedResult - totalZeros
    }
}
