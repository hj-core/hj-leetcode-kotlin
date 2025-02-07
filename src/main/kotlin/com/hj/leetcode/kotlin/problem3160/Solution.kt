package com.hj.leetcode.kotlin.problem3160

/**
 * LeetCode page: [3160. Find the Number of Distinct Colors Among the Balls](https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the number of `queries`.
    fun queryResults(
        limit: Int,
        queries: Array<IntArray>,
    ): IntArray {
        val colored = HashMap<Int, Int>() // entry=(label, color)
        val colorFreq = HashMap<Int, Int>() // entry=(color, freq)

        return IntArray(queries.size) { index ->
            val (label, newColor) = queries[index]

            colored[label]?.let { oldColor ->
                colorFreq.compute(oldColor) { _, freq -> if (freq == 1) null else checkNotNull(freq) - 1 }
            }
            colored[label] = newColor
            colorFreq.compute(newColor) { _, freq -> 1 + (freq ?: 0) }

            colorFreq.size
        }
    }
}
