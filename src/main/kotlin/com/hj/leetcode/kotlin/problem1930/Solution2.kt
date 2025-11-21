package com.hj.leetcode.kotlin.problem1930

/**
 * LeetCode page: [1930. Unique Length-3 Palindromic Subsequences](https://leetcode.com/problems/unique-length-3-palindromic-subsequences/);
 */
class Solution2 {
    // Complexity:
    // Time O(N+K^2) and Space O(K^2) where N is the length of s
    // and K is the number of possible chars.
    fun countPalindromicSubsequence(s: String): Int {
        val queries = prepareQueries(s)
        queries.sortBy { it[0] }

        var result = 0
        var sIdx = 0
        val currFreq = IntArray(26)
        val startFreq = Array(26) { intArrayOf() }

        for (query in queries) {
            val keyIdx = query[0]
            val c = query[1]
            val isStart = query[2] == 0

            while (sIdx < keyIdx) {
                currFreq[s[sIdx] - 'a']++
                sIdx++
            }

            if (isStart) {
                startFreq[c] = currFreq.clone()
            } else {
                result +=
                    (0..<26).count {
                        currFreq[it] > startFreq[c][it]
                    }
                if (currFreq[c] == startFreq[c][c] + 1) {
                    result--
                }
            }
        }
        return result
    }

    private fun prepareQueries(
        s: String,
    ): MutableList<IntArray> {
        val charIndexRange = computeCharIndexRange(s)
        val queries = mutableListOf<IntArray>()
        for ((c, idxRange) in charIndexRange.withIndex()) {
            val (first, last) = idxRange
            if (first < last) {
                queries.add(intArrayOf(first, c, 0))
                queries.add(intArrayOf(last, c, 1))
            }
        }
        return queries
    }

    private fun computeCharIndexRange(
        s: String,
    ): Array<IntArray> {
        val idxRange = Array(26) { intArrayOf(-1, -1) }
        for ((i, char) in s.withIndex()) {
            val c = char - 'a'
            if (idxRange[c][0] == -1) {
                idxRange[c][0] = i
            }
            idxRange[c][1] = i
        }
        return idxRange
    }
}
