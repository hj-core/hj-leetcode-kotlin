package com.hj.leetcode.kotlin.problem1405

import kotlin.math.min

/**
 * LeetCode page: [1405. Longest Happy String](https://leetcode.com/problems/longest-happy-string/submissions/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N equals (a+b+c-max(a,b,c)).
     */
    fun longestDiverseString(
        a: Int,
        b: Int,
        c: Int,
    ): String {
        val sortedChars =
            listOf(
                CharCount('a', a),
                CharCount('b', b),
                CharCount('c', c),
            ).sortedBy { it.count }

        return if (hasDominantChar(a, b, c)) {
            resultWithDominantChar(sortedChars)
        } else {
            resultWithoutDominantChar(sortedChars)
        }
    }

    private data class CharCount(
        val char: Char,
        val count: Int,
    )

    private fun hasDominantChar(
        a: Int,
        b: Int,
        c: Int,
    ): Boolean {
        val max = listOf(a, b, c).max()
        return max * 2 > a + b + c
    }

    private fun resultWithDominantChar(sortedChars: List<CharCount>): String {
        val (x, y, z) = sortedChars
        val longestLength = min(x.count + y.count + z.count, 3 * (x.count + y.count) + 2)
        val result = StringBuilder(longestLength)
        var extraZ = min(z.count - (x.count + y.count), x.count + y.count + 2)
        // Target string resembles 'cca,ccb,cc'
        for (i in listOf(x, y)) {
            repeat(i.count) {
                if (extraZ > 0) {
                    result.append(z.char)
                    extraZ -= 1
                }
                result.append("${z.char}${i.char}")
            }
        }
        repeat(extraZ) {
            result.append(z.char)
        }
        return result.toString()
    }

    private fun resultWithoutDominantChar(sortedChars: List<CharCount>): String {
        val (x, y, z) = sortedChars
        val longestLength = x.count + y.count + z.count
        val result = StringBuilder(longestLength)
        var extraZ = z.count - y.count
        // Target string resembles 'ccba,cba,cb'
        repeat(x.count) {
            if (extraZ > 0) {
                result.append(z.char)
                extraZ -= 1
            }
            result.append("${z.char}${y.char}${x.char}")
        }
        repeat(y.count - x.count) {
            if (extraZ > 0) {
                result.append(z.char)
                extraZ -= 1
            }
            result.append("${z.char}${y.char}")
        }
        return result.toString()
    }
}
