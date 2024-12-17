package com.hj.leetcode.kotlin.problem2182

import kotlin.math.min

/**
 * LeetCode page: [2182. Construct String With Repeat Limit](https://leetcode.com/problems/construct-string-with-repeat-limit/);
 */
class Solution {
    /* Complexity:
     * Time O(N+K) and Space O(N+K)
     * where N is the length of s and K is the size of possible characters.
     */
    fun repeatLimitedString(
        s: String,
        repeatLimit: Int,
    ): String =
        buildString(s.length) {
            val counts = countsSortedByChar(s)
            while (counts.isNotEmpty()) {
                greedilyAppendNext(counts, repeatLimit)
            }
        }

    private fun countsSortedByChar(s: String): MutableList<CharCount> {
        val counts = IntArray(26)
        for (c in s) {
            counts[c - 'a']++
        }

        return counts.foldIndexed(mutableListOf()) { index, acc, count ->
            acc.apply {
                if (count > 0) add(CharCount('a' + index, count))
            }
        }
    }

    private class CharCount(
        var char: Char,
        var count: Int,
    )

    private fun StringBuilder.greedilyAppendNext(
        sortedCounts: MutableList<CharCount>, // sorted by char
        repeatLimit: Int,
    ) {
        val largest = sortedCounts.last()
        repeat(min(largest.count, repeatLimit)) {
            append(largest.char)
        }

        if (largest.count <= repeatLimit || sortedCounts.size == 1) {
            sortedCounts.removeLast()
        } else {
            largest.count -= repeatLimit

            val secondLargest = sortedCounts[sortedCounts.size - 2]
            append(secondLargest.char)
            if (secondLargest.count == 1) {
                sortedCounts.removeAt(sortedCounts.size - 2)
            } else {
                secondLargest.count -= 1
            }
        }
    }
}
