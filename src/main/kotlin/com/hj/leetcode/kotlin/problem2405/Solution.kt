package com.hj.leetcode.kotlin.problem2405

/**
 * LeetCode page: [2405. Optimal Partition of String](https://leetcode.com/problems/optimal-partition-of-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space(1) where N is the length of s;
     */
    fun partitionString(s: String): Int {
        val charExistence = BooleanArray(26)
        var numSubString = 1
        for (lowercase in s) {
            val charIndex = lowercase - 'a'
            val isRepeated = charExistence[charIndex]
            if (isRepeated) {
                numSubString++
                charExistence.fill(false)
            }
            charExistence[charIndex] = true
        }
        return numSubString
    }
}