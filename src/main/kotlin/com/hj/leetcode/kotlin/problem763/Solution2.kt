package com.hj.leetcode.kotlin.problem763

/**
 * LeetCode page: [763. Partition Labels](https://leetcode.com/problems/partition-labels/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length s.
    // Here, we treat the size of the charset, which is 26 in this problem, as constant.
    fun partitionLabels(s: String): List<Int> {
        val lastIndices = IntArray(26) { s.length }
        for ((index, c) in s.withIndex()) {
            lastIndices[c - 'a'] = index
        }

        var partitionStart = 0
        var partitionEnd = -1
        val result = mutableListOf<Int>()

        for ((index, c) in s.withIndex()) {
            partitionEnd = maxOf(partitionEnd, lastIndices[c - 'a'])

            if (index == partitionEnd) {
                result.add(index - partitionStart + 1)
                partitionStart = index + 1
            }
        }
        return result
    }
}
