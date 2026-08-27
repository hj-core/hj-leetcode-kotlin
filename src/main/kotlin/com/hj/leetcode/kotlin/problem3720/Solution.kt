package com.hj.leetcode.kotlin.problem3720

/**
 * LeetCode page: [3720. Lexicographically Smallest Permutation Greater Than Target](https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/);
 */
class Solution {
    // Complexity:
    // Time O(KN) and Space O(K+N) where N is the length of s and target
    // and K is the number of lowercase letters.
    fun lexGreaterPermutation(
        s: String,
        target: String,
    ): String {
        var (prefixLen, charFreq) = findLongestPrefixAndRemainingChars(s, target)

        if (prefixLen == target.length) {
            prefixLen--
            charFreq[target[prefixLen] - 'a']++

            prefixLen--
            if (prefixLen >= 0) {
                charFreq[target[prefixLen] - 'a']++
            }
        }

        val result = StringBuilder()
        while (prefixLen >= 0) {
            val cIdx = target[prefixLen] - 'a'
            val next = (cIdx + 1..<26).firstOrNull { charFreq[it] > 0 }
            if (next != null) {
                result.append(target, 0, prefixLen)
                result.append('a' + next)
                charFreq[next]--
                break
            }

            prefixLen--
            if (prefixLen >= 0) {
                charFreq[target[prefixLen] - 'a']++
            }
        }

        if (prefixLen == -1) {
            return ""
        }
        for (cIdx in 0..<26) {
            repeat(charFreq[cIdx]) {
                val c = 'a' + cIdx
                result.append(c)
            }
        }
        return result.toString()
    }

    private fun findLongestPrefixAndRemainingChars(
        s: String,
        target: String,
    ): Pair<Int, IntArray> {
        val charFreq = IntArray(26)
        for (c in s) {
            charFreq[c - 'a']++
        }

        var prefixLen = 0
        for (c in target) {
            val cIdx = c - 'a'
            if (charFreq[cIdx] == 0) {
                break
            }

            prefixLen++
            charFreq[cIdx]--
        }

        return Pair(prefixLen, charFreq)
    }
}
