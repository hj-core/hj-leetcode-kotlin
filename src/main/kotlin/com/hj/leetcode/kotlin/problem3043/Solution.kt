package com.hj.leetcode.kotlin.problem3043

import kotlin.math.max

/**
 * LeetCode page: *[3043. Find the Length of the Longest Common Prefix](https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N) where N is the total number of digits in arr1
     * and M is the total number of digits in arr2.
     */
    fun longestCommonPrefix(
        arr1: IntArray,
        arr2: IntArray,
    ): Int {
        val allPrefixes1 = allPrefixes(arr1)
        var maxCommonPrefix = -1
        for (num in arr2) {
            prefixes(num).filter { it in allPrefixes1 }.maxOrNull()?.let {
                maxCommonPrefix = max(maxCommonPrefix, it)
            }
        }
        return if (maxCommonPrefix == -1) 0 else maxCommonPrefix.toString().length
    }

    private fun allPrefixes(arr: IntArray): Set<Int> =
        buildSet {
            for (num in arr) {
                addAll(prefixes(num))
            }
        }

    private fun prefixes(num: Int): Sequence<Int> =
        sequence {
            var prefix = num
            while (prefix > 0) {
                yield(prefix)
                prefix /= 10
            }
        }
}
