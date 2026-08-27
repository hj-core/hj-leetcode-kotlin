package com.hj.leetcode.kotlin.problem2904

/**
 * LeetCode page: [2904. Shortest and Lexicographically Smallest Beautiful String](https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/);
 */
class Solution {
    // Complexity:
    // Time O(kN) and Space O(N) where N is the length of s.
    fun shortestBeautifulSubstring(
        s: String,
        k: Int,
    ): String {
        val ones = s.indices.filter { s[it] == '1' }
        if (ones.size < k) {
            return ""
        }

        // Find the indices of ones that correspond to the starting indices of those
        // shortest beautiful substrings.
        val candidates = mutableListOf<Int>()
        var shortestLen = s.length + 1
        for (i in 0..(ones.size - k)) {
            val len = ones[i + k - 1] - ones[i] + 1
            if (len < shortestLen) {
                candidates.clear()
                shortestLen = len
                candidates.add(i)
            } else if (len == shortestLen) {
                candidates.add(i)
            }
        }

        // Find the smallest candidate using an approach similar to radix sort.
        var remaining = candidates.size
        for (seg in 0..<(k - 1)) {
            var maxSegLen = -1
            var last = -1
            for (i in 0..<remaining) {
                val candidate = candidates[i]
                val segLen = ones[candidate + seg + 1] - ones[candidate + seg]
                if (segLen > maxSegLen) {
                    last = 0
                    maxSegLen = segLen
                    candidates[last] = candidate
                } else if (segLen == maxSegLen) {
                    last++
                    candidates[last] = candidate
                }
            }

            remaining = last + 1
            if (remaining == 1) {
                break
            }
        }

        val shortestStart = ones[candidates[0]]
        return s.substring(shortestStart, shortestStart + shortestLen)
    }
}
