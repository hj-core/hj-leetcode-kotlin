package com.hj.leetcode.kotlin.problem763

/**
 * LeetCode page: [763. Partition Labels](https://leetcode.com/problems/partition-labels/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length s.
    // Here, we treat the size of the charset, which is 26 in this problem, as constant.
    fun partitionLabels(s: String): List<Int> {
        val intervals = findFirstLastIndices(s)
        intervals.sortBy { it[0] }

        var partitionStart = 0
        var partitionEnd = 0
        val result = mutableListOf<Int>()

        for ((first, last) in intervals) {
            if (first == -1) {
                continue
            }
            if (first > partitionEnd) {
                result.add(partitionEnd - partitionStart + 1)
                partitionStart = first
            }
            partitionEnd = maxOf(partitionEnd, last)
        }
        result.add(partitionEnd - partitionStart + 1)
        return result
    }

    // findFirstLastIndices returns an array where it[c - 'a'] contains the first and
    // last indices of letter c in the string s.
    //
    // Clients must ensure that s consists of only lowercase letters.
    // Letters that don't appear in s have a first index of -1.
    private fun findFirstLastIndices(s: String): Array<IntArray> {
        val result = Array(26) { intArrayOf(-1, -1) }

        for ((index, c) in s.withIndex()) {
            val cIndex = c - 'a'
            if (result[cIndex][0] == -1) {
                result[cIndex][0] = index
            }
            result[cIndex][1] = index
        }
        return result
    }
}
