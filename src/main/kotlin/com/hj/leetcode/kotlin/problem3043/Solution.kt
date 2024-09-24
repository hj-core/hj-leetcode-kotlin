package com.hj.leetcode.kotlin.problem3043

/**
 * LeetCode page: *[3043. Find the Length of the Longest Common Prefix](https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N+M) where N is the total number of digits in arr1
     * and M is the total number of digits in arr2.
     */
    fun longestCommonPrefix(
        arr1: IntArray,
        arr2: IntArray,
    ): Int {
        val prefixes1 = allPrefixes(arr1)
        val prefixes2 = allPrefixes(arr2)
        val maxCommon = prefixes1.asSequence().filter { it in prefixes2 }.maxOrNull()
        if (maxCommon == null) {
            return 0
        }
        return maxCommon.toString().length
    }

    private fun allPrefixes(arr: IntArray): Set<Int> {
        val result = mutableSetOf<Int>()
        for (num in arr) {
            var iNum = num
            while (iNum > 0) {
                if (!result.add(iNum)) {
                    break
                }
                iNum /= 10
            }
        }
        return result
    }
}
