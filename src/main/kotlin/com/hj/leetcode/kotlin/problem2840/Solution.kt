package com.hj.leetcode.kotlin.problem2840

/**
 * LeetCode page: [2840. Check if Strings Can be Made Equal With Operations II](https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(M) where N is the length of s1 and s2 and M is
    // the size of char set (i.e. 26).
    fun checkStrings(
        s1: String,
        s2: String,
    ): Boolean {
        val freqDiff = Array(2) { IntArray(26) }
        for (i in s1.indices) {
            freqDiff[i and 1][s1[i] - 'a']++
            freqDiff[i and 1][s2[i] - 'a']--
        }

        return freqDiff.all { it.all { freq -> freq == 0 } }
    }
}
