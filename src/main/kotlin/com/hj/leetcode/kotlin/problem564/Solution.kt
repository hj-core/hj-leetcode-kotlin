package com.hj.leetcode.kotlin.problem564

import java.util.regex.Pattern
import kotlin.math.abs

/**
 * LeetCode page: [564. Find the Closest Palindrome](https://leetcode.com/problems/find-the-closest-palindrome/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of n assuming that
     * REGEX matching is O(N) as well;
     */
    fun nearestPalindromic(n: String): String {
        // Cases like 1000 or 1001 should return 999
        if (Pattern.compile("^10*[01]$").matcher(n).matches()) {
            return "9".repeat(n.length - 1)
        }
        // Cases like 999 should return 1001
        if (n.length > 1 && n.all { it == '9' }) {
            return "1${"0".repeat(n.length - 1)}1"
        }

        val firstHalf = n.slice(0..(n.length - 1) / 2).toLong()
        val evenLength = n.length % 2 == 0
        val candidates =
            mutableListOf<Long>().apply {
                add(toPalindrome(firstHalf + 1, evenLength))
                add(toPalindrome(firstHalf - 1, evenLength))
                if (n != n.reversed()) {
                    add(toPalindrome(firstHalf, evenLength))
                }
            }
        val nValue = n.toLong()
        candidates.sortWith(compareBy({ abs(it - nValue) }, { it }))
        return candidates[0].toString()
    }

    private fun toPalindrome(
        base: Long,
        evenLength: Boolean,
    ): Long =
        buildString {
            append(base)
            val baseLength = this.length
            if (evenLength) {
                append(this[baseLength - 1])
            }
            for (i in baseLength - 2 downTo 0) {
                append(this[i])
            }
        }.toLong()
}
