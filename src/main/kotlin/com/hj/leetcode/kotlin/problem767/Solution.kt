package com.hj.leetcode.kotlin.problem767

/**
 * LeetCode page: [767. Reorganize String](https://leetcode.com/problems/reorganize-string/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of s;
     */
    fun reorganizeString(s: String): String {
        val lowercaseCount = s.lowercaseCount()

        val maxCountIndex = lowercaseCount.indexOfMax()
        if (lowercaseCount[maxCountIndex] > Math.ceil(s.length / 2.0)) {
            return ""
        }

        val result = StringBuilder(s)
        var evenIndex = 0
        val maxChar = 'a' + maxCountIndex
        while (lowercaseCount[maxCountIndex] > 0) {
            result[evenIndex] = maxChar
            lowercaseCount[maxCountIndex]--
            evenIndex += 2
        }

        for (charIndex in lowercaseCount.indices) {
            val char = 'a' + charIndex
            while (lowercaseCount[charIndex] > 0 && evenIndex < s.length) {
                result[evenIndex] = char
                lowercaseCount[charIndex]--
                evenIndex += 2
            }
        }

        var oddIndex = 1
        for (charIndex in lowercaseCount.indices) {
            val char = 'a' + charIndex
            while (lowercaseCount[charIndex] > 0) {
                result[oddIndex] = char
                lowercaseCount[charIndex]--
                oddIndex += 2
            }
        }

        return result.toString()
    }

    private fun String.lowercaseCount(): IntArray {
        val result = IntArray(26)
        for (char in this) {
            result[char - 'a']++
        }
        return result
    }

    private fun IntArray.indexOfMax(): Int {
        if (this.isEmpty()) {
            return -1
        }

        var result = 0
        for ((index, value) in this.withIndex()) {
            if (value > this[result]) {
                result = index
            }
        }
        return result
    }
}