package com.hj.leetcode.kotlin.problem3003

/**
 * LeetCode page: [3003. Maximize the Number of Partitions After Operations](https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M is the number of possible
    // lowercase letters (i.e., 26) and N is the length of s.
    fun maxPartitionsAfterOperations(
        s: String,
        k: Int,
    ): Int {
        if (k == 26) {
            return 1
        }

        val maxLen = findLongestPrefixLength(s, k)
        val minLen = findLongestPrefixLength(s, k - 1)

        val suffixFreq = if (k > 13) findSuffixFreq(s) else emptyArray()

        // noChange[i]:= the maximum partitions of s[i:] if we cannot change letters
        val noChange = IntArray(s.length + 1)
        for (i in s.indices.reversed()) {
            noChange[i] = 1 + noChange[i + maxLen[i]]
        }

        // canSwap[i]:= the maximum partitions of s[i:] if we can make a change
        val mayChange = IntArray(s.length + 1)

        for (i in s.indices.reversed()) {
            // No change is made
            mayChange[i] = 1 + mayChange[i + maxLen[i]]

            // A change in s[i..<i+minLen[i]]
            if (minLen[i] >= k) {
                val newStart = i + minLen[i]
                mayChange[i] = maxOf(mayChange[i], 1 + noChange[newStart])
            }

            // Change s[i+minLen[i]]
            if (maxLen[i] > minLen[i] + 1) {
                val newStart = i + minLen[i] + 1
                mayChange[i] = maxOf(mayChange[i], 1 + noChange[newStart])
            }

            // Change s[i+minLen[i]+1]
            if (maxLen[i] > minLen[i] + 2) {
                val newStart = i + minLen[i] + 1

                var hasDistinct = suffixFreq.isEmpty()
                if (suffixFreq.isNotEmpty()) {
                    val freq1 = suffixFreq[i]
                    val freq2 = suffixFreq[newStart + maxLen[newStart]]
                    freq1[s[newStart] - 'a']--
                    hasDistinct = freq1.indices.any { freq1[it] == freq2[it] }
                    freq1[s[newStart] - 'a']++
                }

                if (hasDistinct) {
                    mayChange[i] = maxOf(mayChange[i], 2 + noChange[newStart + 1 + minLen[newStart + 1]])
                }
            }
        }

        return mayChange[0]
    }

    private fun findLongestPrefixLength(
        s: String,
        k: Int,
    ): IntArray {
        val result = IntArray(s.length + 1)
        val freq = IntArray(26)
        var uniqueCnt = 0

        var right = s.length - 1
        for (i in s.indices.reversed()) {
            freq[s[i] - 'a']++
            if (freq[s[i] - 'a'] == 1) {
                uniqueCnt++
            }

            while (uniqueCnt > k) {
                freq[s[right] - 'a']--
                if (freq[s[right] - 'a'] == 0) {
                    uniqueCnt--
                }
                right--
            }
            result[i] = right - i + 1
        }
        return result
    }

    private fun findSuffixFreq(s: String): Array<IntArray> {
        val result = Array(s.length + 1) { IntArray(26) }
        for (i in s.indices.reversed()) {
            result[i + 1].copyInto(result[i])
            result[i][s[i] - 'a']++
        }
        return result
    }
}
