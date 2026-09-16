package com.hj.leetcode.kotlin.problem2472

/**
 * LeetCode page: [2472. Maximum Number of Non-overlapping Palindrome Substrings](https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of s.
    fun maxPalindromes(
        s: String,
        k: Int,
    ): Int {
        val manacher = Manacher(s)
        var count = 0
        var i = 0
        while (i <= s.length - k) {
            when {
                manacher.isPalindrome(i, i + k) -> {
                    count++
                    i += k
                }

                manacher.isPalindrome(i, i + k + 1) -> {
                    count++
                    i += k + 1
                }

                else -> {
                    i++
                }
            }
        }

        return count
    }

    private class Manacher(
        s: String,
    ) {
        // maxRadius[i]:= The maximum radius of palindrome center at index i of the expanded s
        private val maxRadius = computeMaxRadius(s)

        private fun computeMaxRadius(s: String): IntArray {
            val s2 = CharArray(s.length * 2) { if (it and 1 == 0) s[it / 2] else '#' }
            val maxRadius = IntArray(s2.size)
            var c = -1
            var r = 0
            for (i in s2.indices) {
                var j = if (i < c + r) minOf(maxRadius[c * 2 - i], c + r - i) else 1
                while (j <= i && i + j < s2.size && s2[i - j] == s2[i + j]) {
                    j++
                }
                maxRadius[i] = j
                if (i + j > c + r) {
                    c = i
                    r = j
                }
            }

            return maxRadius
        }

        fun isPalindrome(
            start: Int,
            end: Int,
        ): Boolean {
            if (end <= start) {
                return false
            }
            if (start < 0 || end > maxRadius.size / 2) {
                return false
            }

            val c = start + end - 1
            return c + maxRadius[c] > (end - 1) * 2
        }
    }
}
