package com.hj.leetcode.kotlin.problem165

import kotlin.math.max

/**
 * LeetCode page: [165. Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(N+M) where N and M are the length
    // of version1 and version2, respectively.
    fun compareVersion(
        version1: String,
        version2: String,
    ): Int {
        val ver1 = version1.split(".")
        val ver2 = version2.split(".")

        for (i in 0..<max(ver1.size, ver2.size)) {
            val rev1 = ver1.getOrElse(i) { "0" }.toInt()
            val rev2 = ver2.getOrElse(i) { "0" }.toInt()

            if (rev1 != rev2) {
                return if (rev1 < rev2) -1 else 1
            }
        }
        return 0
    }
}
