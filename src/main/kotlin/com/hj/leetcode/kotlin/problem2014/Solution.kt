package com.hj.leetcode.kotlin.problem2014

/**
 * LeetCode page: [2014. Longest Subsequence Repeated k Times](https://leetcode.com/problems/longest-subsequence-repeated-k-times/);
 */
class Solution {
    // Complexity:
    // Time O(???) and Space O(???).
    fun longestSubsequenceRepeatedK(
        s: String,
        k: Int,
    ): String {
        val freq = countLowercases(s)
        toMaxOccurrences(freq, k)
        return dfs(pruneChars(s, freq), k, freq)
    }

    // Returns the frequency of each lowercase.
    private fun countLowercases(s: String): IntArray {
        val result = IntArray(26)
        for (c in s) {
            result[c - 'a']++
        }
        return result
    }

    // Modifies the freq to the maximum number of occurrences in a subsequence
    // that is k-repeated.
    private fun toMaxOccurrences(
        freq: IntArray,
        k: Int,
    ) {
        for (i in freq.indices) {
            freq[i] /= k
        }
    }

    // Returns a new string with the characters removed from s that cannot
    // be part of a k-repeated subsequence.
    private fun pruneChars(
        s: String,
        maxOccur: IntArray,
    ): String {
        val builder = StringBuilder()
        for (c in s) {
            if (maxOccur[c - 'a'] > 0) {
                builder.append(c)
            }
        }
        return builder.toString()
    }

    // Returns the longest subsequence that is k-repeated, opting for the
    // lexicographically largest one if there are multiple such subsequences.
    private fun dfs(
        s: String,
        k: Int,
        // Extra occurrence of each lowercase that can be added to the
        // subsequence.
        extraOccur: IntArray,
        subsequence: StringBuilder = StringBuilder(),
    ): String {
        if (!isKRepeated(s, k, subsequence)) {
            return ""
        }

        var result = subsequence.toString()
        for (i in 25 downTo 0) {
            if (extraOccur[i] == 0) {
                continue
            }
            extraOccur[i]--
            subsequence.append('a' + i)
            val next = dfs(s, k, extraOccur, subsequence)

            if (next.length > result.length) {
                result = next
            }
            subsequence.deleteCharAt(subsequence.length - 1)
            extraOccur[i]++
        }
        return result
    }

    // Returns whether the subsequence is k-repeated.
    private fun isKRepeated(
        s: String,
        k: Int,
        subsequence: StringBuilder,
    ): Boolean {
        if (subsequence.isEmpty()) {
            return true
        }

        var cnt = 0
        var i = 0
        for (c in s) {
            if (c == subsequence[i]) {
                i++
                if (i == subsequence.length) {
                    i = 0
                    cnt++
                    if (cnt == k) {
                        return true
                    }
                }
            }
        }
        return false
    }
}
