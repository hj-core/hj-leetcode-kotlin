package com.hj.leetcode.kotlin.problem2901

/**
 * LeetCode page: [2901. Longest Unequal Adjacent Groups Subsequence II](https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii/);
 */
class Solution {
    // Complexity:
    // Time O(L*N^2) and Space O(NL) where N and L are the number of words and the average
    // length of words, respectively.
    fun getWordsInLongestSubsequence(
        words: Array<String>,
        groups: IntArray,
    ): List<String> {
        // dpLen[i] ::= the longest length of the subsequence starting with words[i]
        val dpLen = IntArray(words.size)
        // dpNext[i] ::= the seconde index of the subsequence corresponding to dpLen[i]
        val dpNext = IntArray(words.size)

        var bestStart = words.lastIndex
        dpLen[bestStart] = 1
        dpNext[bestStart] = bestStart

        for (start in words.lastIndex - 1 downTo 0) {
            dpLen[start] = 1
            dpNext[start] = start

            for (next in start + 1..<words.size) {
                if (groups[next] != groups[start] &&
                    dpLen[start] < 1 + dpLen[next] &&
                    isHammingDistanceOne(words[start], words[next])
                ) {
                    dpLen[start] = 1 + dpLen[next]
                    dpNext[start] = next
                }
                if (next + dpLen[start] > words.size) {
                    break
                }
            }
            if (dpLen[start] > dpLen[bestStart]) {
                bestStart = start
            }
        }
        return buildSequences(words, bestStart, dpNext)
    }

    private fun isHammingDistanceOne(
        a: String,
        b: String,
    ): Boolean {
        if (a.length != b.length) {
            return false
        }
        var diff = 0
        for (i in a.indices) {
            if (a[i] != b[i]) {
                diff++
                if (diff == 2) {
                    break
                }
            }
        }
        return diff == 1
    }

    private fun buildSequences(
        words: Array<String>,
        bestStart: Int,
        dpNext: IntArray,
    ): List<String> =
        buildList {
            add(words[bestStart])
            var curr = bestStart
            while (dpNext[curr] != curr) {
                curr = dpNext[curr]
                add(words[curr])
            }
        }
}
