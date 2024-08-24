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

        val candidates = mutableListOf<Long>()
        val mid = (n.length - 1) / 2
        val leftHalf = n.slice(0..mid).toInt()
        val pivot = mid - if (n.length % 2 == 0) 0 else 1
        candidates.add(
            buildString {
                append(leftHalf + 1)
                for (i in pivot downTo 0) {
                    append(this[i])
                }
            }.toLong(),
        )
        candidates.add(
            buildString {
                append(leftHalf - 1)
                for (i in pivot downTo 0) {
                    append(this[i])
                }
            }.toLong(),
        )
        if (n != n.reversed()) {
            candidates.add(
                buildString {
                    append(leftHalf)
                    for (i in pivot downTo 0) {
                        append(this[i])
                    }
                }.toLong(),
            )
        }
        val nValue = n.toLong()
        candidates.sortWith(compareBy({ abs(it - nValue) }, { it }))
        return candidates[0].toString()
    }
}
