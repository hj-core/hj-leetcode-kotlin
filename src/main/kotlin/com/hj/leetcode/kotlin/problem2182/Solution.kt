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
            val counts = charCounts(s)
            while (counts.isNotEmpty()) {
                val largest = counts.last()
                if (largest.count <= repeatLimit || counts.size == 1) {
                    val times = min(largest.count, repeatLimit)
                    repeat(times) { append(largest.char) }
                    counts.removeLast()
                } else {
                    repeat(repeatLimit) { append(largest.char) }
                    largest.count -= repeatLimit

                    val secondLargest = counts[counts.size - 2]
                    append(secondLargest.char)
                    if (secondLargest.count == 1) {
                        counts.removeAt(counts.size - 2)
                    } else {
                        secondLargest.count -= 1
                    }
                }
            }
        }

    // Return count of each lowercase in s in lexicographical order.
    private fun charCounts(s: String): MutableList<CharCount> {
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
}
