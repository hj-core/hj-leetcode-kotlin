package com.hj.leetcode.kotlin.problem3734

/**
 * LeetCode page: [3734. Lexicographically Smallest Palindromic Permutation Greater Than Target](https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(K+N) where N is the length of s and K is the
    // number of lowercases.
    fun lexPalindromicPermutation(
        s: String,
        target: String,
    ): String {
        if (s.length == 1) {
            return if (s > target) s else ""
        }

        val freeChars = countChars(s)
        if (!checkPalindromic(freeChars)) {
            return ""
        }

        val result = CharArray(s.length)
        if (s.length and 1 == 1) {
            fillMiddle(freeChars, result)
        }

        var prefixLen = fillLongestPrefix(target, freeChars, result)
        if (prefixLen == s.length / 2) {
            if (checkHalfLengthPrefix(target, result)) {
                return String(result)
            }
            prefixLen--
            freeChars[target[prefixLen] - 'a'] += 2
        }

        var existMask =
            freeChars.foldIndexed(0) { cIdx, mask, count ->
                if (count > 0) mask or (1 shl cIdx) else mask
            }

        while (prefixLen >= 0) {
            val cIdx = target[prefixLen] - 'a'
            if (existMask shr cIdx > 1) {
                val next = ((cIdx + 1)..<26).first { freeChars[it] > 0 }
                setCharPair(freeChars, next, prefixLen, result)
                fillRemainingChars(freeChars, prefixLen + 1, result)

                return String(result)
            }

            prefixLen--
            if (prefixLen >= 0) {
                val cIdx = target[prefixLen] - 'a'
                freeChars[cIdx] += 2
                if (freeChars[cIdx] == 2) {
                    existMask = existMask or (1 shl cIdx)
                }
            }
        }

        return ""
    }

    private fun countChars(s: String): IntArray {
        val count = IntArray(26)
        for (c in s) {
            count[c - 'a']++
        }
        return count
    }

    private fun checkPalindromic(freeChars: IntArray): Boolean =
        freeChars.count { it and 1 == 1 } <= 1

    private fun fillMiddle(
        freeChars: IntArray,
        result: CharArray,
    ): Boolean {
        val cIdx = freeChars.indexOfFirst { it and 1 == 1 }
        if (cIdx == -1) {
            return false
        }
        result[result.size / 2] = 'a' + cIdx
        freeChars[cIdx]--
        return true
    }

    private fun fillLongestPrefix(
        target: String,
        freeChars: IntArray,
        result: CharArray,
    ): Int {
        val halfN = target.length / 2
        for (i in 0..<halfN) {
            val cIdx = target[i] - 'a'
            if (freeChars[cIdx] >= 2) {
                setCharPair(freeChars, cIdx, i, result)
            } else {
                return i
            }
        }
        return halfN
    }

    private fun setCharPair(
        freeChars: IntArray,
        cIdx: Int,
        at: Int,
        result: CharArray,
    ) {
        result[at] = 'a' + cIdx
        result[result.lastIndex - at] = result[at]
        freeChars[cIdx] -= 2
    }

    private fun checkHalfLengthPrefix(
        target: String,
        result: CharArray,
    ): Boolean {
        val halfLen = target.length / 2
        for (i in halfLen..<target.length) {
            if (result[i] != target[i]) {
                return result[i] > target[i]
            }
        }
        return false
    }

    private fun fillRemainingChars(
        freeChars: IntArray,
        start: Int,
        result: CharArray,
    ) {
        var i = start
        for (cIdx in 0..<26) {
            repeat(freeChars[cIdx] / 2) {
                result[i] = 'a' + cIdx
                result[result.lastIndex - i] = result[i]
                i++
            }
        }
    }
}
