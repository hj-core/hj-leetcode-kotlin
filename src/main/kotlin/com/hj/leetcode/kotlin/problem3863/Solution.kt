package com.hj.leetcode.kotlin.problem3863

/**
 * LeetCode page: [3863. Minimum Operations to Sort a String](https://leetcode.com/problems/minimum-operations-to-sort-a-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun minOperations(s: String): Int {
        if (s.length <= 2) {
            return if (s[0] <= s.last()) 0 else -1
        }

        val midRange = 2..<(s.length - 1)
        var midMin = s[1]
        var midMax = s[1]
        var isMidSorted = true
        for (i in midRange) {
            midMin = minOf(midMin, s[i])
            midMax = maxOf(midMax, s[i])
            isMidSorted = isMidSorted && s[i - 1] <= s[i]
        }

        if (s.last() >= midMax) {
            return when {
                s[0] <= midMin -> if (isMidSorted) 0 else 1
                s[0] <= midMax -> 1
                else -> if (s[0] <= s.last()) 1 else 2
            }
        }

        if (s.last() >= midMin) {
            return if (s[0] <= midMin) 1 else 2
        }

        return when {
            s[0] <= midMin -> if (s[0] <= s.last()) 1 else 2
            s[0] <= midMax -> 2
            else -> 3
        }
    }
}
