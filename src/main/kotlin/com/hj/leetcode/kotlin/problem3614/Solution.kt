package com.hj.leetcode.kotlin.problem3614

/**
 * LeetCode page: [3614. Process String with Special Operations II](https://leetcode.com/problems/process-string-with-special-operations-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun processStr(
        s: String,
        k: Long,
    ): Char {
        var lastIndex = computeFinalLength(s) - 1
        var k = k
        if (k > lastIndex) {
            return '.'
        }

        for (i in s.indices.reversed()) {
            when (s[i]) {
                in 'a'..'z' -> {
                    if (lastIndex == k) {
                        return s[i]
                    }
                    lastIndex--
                }

                '*' -> {
                    lastIndex++
                }

                '#' -> {
                    lastIndex /= 2
                    if (k > lastIndex) {
                        k -= lastIndex + 1
                    }
                }

                '%' -> {
                    k = lastIndex - k
                }
            }
        }

        throw IllegalStateException("Unreachable code")
    }

    private fun computeFinalLength(s: String): Long =
        s.fold(0L) { len, c ->
            when (c) {
                in 'a'..'z' -> len + 1
                '*' -> maxOf(len - 1, 0)
                '#' -> len * 2
                '%' -> len
                else -> throw IllegalArgumentException("Invalid len $c")
            }
        }
}
