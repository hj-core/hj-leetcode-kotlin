package com.hj.leetcode.kotlin.problem165

/**
 * LeetCode page: [165. Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers/);
 */
class Solution2 {
    // Complexity:
    // Time O(N+M) and Space O(1) where N and M are the length of
    // version1 and version2, respectively.
    fun compareVersion(
        version1: String,
        version2: String,
    ): Int {
        var end1 = -1
        var end2 = -1

        while (end1 < version1.length || end2 < version2.length) {
            end1++
            var rev1 = 0
            while (end1 < version1.length && version1[end1] != '.') {
                rev1 = rev1 * 10 + (version1[end1] - '0')
                end1++
            }

            end2++
            var rev2 = 0
            while (end2 < version2.length && version2[end2] != '.') {
                rev2 = rev2 * 10 + (version2[end2] - '0')
                end2++
            }

            when {
                rev1 < rev2 -> return -1
                rev1 > rev2 -> return 1
            }
        }

        return 0
    }
}
