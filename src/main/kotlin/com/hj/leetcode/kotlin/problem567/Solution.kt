package com.hj.leetcode.kotlin.problem567

/**
 * LeetCode page: [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/);
 */
class Solution {
    /* Complexity:
     * Time O(|s2|) and Space O(1);
     */
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) return false

        val targetCount = countCharFrequency(s1)
        val currWindowCount = countCharFrequency(s2, s1.indices)
        var numMatched = (0 until 26).count { currWindowCount[it] == targetCount[it] }
        if (numMatched == 26) return true

        for (index in s1.length until s2.length) {
            val popCountIndex = s2[index - s1.length] - 'a'
            currWindowCount[popCountIndex]--
            when (currWindowCount[popCountIndex]) {
                targetCount[popCountIndex] -> numMatched++
                targetCount[popCountIndex] - 1 -> numMatched--
            }

            val pushCountIndex = s2[index] - 'a'
            currWindowCount[pushCountIndex]++
            when (currWindowCount[pushCountIndex]) {
                targetCount[pushCountIndex] -> numMatched++
                targetCount[pushCountIndex] + 1 -> numMatched--
            }

            if (numMatched == 26) return true
        }
        return false
    }

    private fun countCharFrequency(
        lowercase: String,
        indexRange: IntRange = lowercase.indices
    ): IntArray {
        val count = IntArray(26)
        for (index in indexRange) {
            count[lowercase[index] - 'a']++
        }
        return count
    }
}