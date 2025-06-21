package com.hj.leetcode.kotlin.problem3085

/**
 * LeetCode page: [3085. Minimum Deletions to Make String K-Special](https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/);
 */
class Solution {
    // Complexity;
    // Time O(N+SLogS) and Space O(S) where N is the length of word and
    // S is the number of allowed characters.
    fun minimumDeletions(
        word: String,
        k: Int,
    ): Int {
        val freq = IntArray(26)
        for (c in word) {
            freq[c - 'a']++
        }

        freq.sort()
        var result = word.length
        var prefix = 0 // The first index where freq[prefix] == freq[i]
        var prefixSum = 0 // The sum of freq[0..<prefix]
        var suffix = 0 // The first index where freq[suffix] > freq[i]+k
        var suffixSum = word.length // The sum of freq[suffix..]

        // Try each freq[i] as the minimum frequency after deletions
        for (i in freq.indices) {
            if (freq[i] == 0) {
                prefix = i
                suffix = i
                continue
            }

            while (prefix < i && freq[prefix] < freq[i]) {
                prefixSum += freq[prefix]
                prefix++
            }
            while (suffix < freq.size && freq[suffix] <= freq[i] + k) {
                suffixSum -= freq[suffix]
                suffix++
            }

            val subResult = prefixSum + suffixSum - (freq[i] + k) * (freq.size - suffix)
            result = minOf(result, subResult)
        }
        return result
    }
}
