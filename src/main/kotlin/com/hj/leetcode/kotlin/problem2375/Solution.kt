package com.hj.leetcode.kotlin.problem2375

/**
 * LeetCode page: [2375. Construct Smallest Number From DI String](https://leetcode.com/problems/construct-smallest-number-from-di-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of pattern.
    fun smallestNumber(pattern: String): String =
        buildString {
            var nextDigit = 1
            var index = 0
            while (index < pattern.length) {
                if (pattern[index] == 'I') {
                    append(nextDigit)
                    nextDigit++
                    index++
                } else {
                    val dStreak = dStreak(pattern, index)
                    for (digit in (nextDigit + dStreak) downTo nextDigit) {
                        append(digit)
                    }
                    nextDigit += dStreak + 1
                    index += dStreak + 1
                }
            }

            if (index == pattern.length) {
                append(nextDigit)
            }
        }

    // `dStreak` returns the length of `D` streak in `pattern` starting from `start`.
    private fun dStreak(
        pattern: String,
        start: Int,
    ): Int {
        var endExclusive = start
        while (endExclusive < pattern.length && pattern[endExclusive] == 'D') {
            endExclusive++
        }
        return endExclusive - start
    }
}
