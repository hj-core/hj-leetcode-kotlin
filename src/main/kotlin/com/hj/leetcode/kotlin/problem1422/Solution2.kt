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
        var leftZeros = 0
        var leftOnes = 0
        var totalOnes = 0
        var equivalentResult = -1
        for (index in 0..<s.lastIndex) {
            if (s[index] == '0') {
                leftZeros++
            } else {
                leftOnes++
                totalOnes++
            }
            equivalentResult = max(equivalentResult, leftZeros - leftOnes)
        }
        totalOnes += if (s.last() == '1') 1 else 0
        return equivalentResult + totalOnes
    }
}