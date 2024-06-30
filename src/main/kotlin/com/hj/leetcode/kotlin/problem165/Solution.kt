package com.hj.leetcode.kotlin.problem165

import kotlin.math.max

/**
 * LeetCode page: [165. Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(M+N) where M is the length of version1
     * and N is the length of version2;
     */
    fun compareVersion(version1: String, version2: String): Int {
        val segments1 = version1.split(".")
        val segments2 = version2.split(".")

        for (i in 0..<max(segments1.size, segments2.size)) {
            val segment1 = segments1.getOrElse(i) { "0" }.toInt()
            val segment2 = segments2.getOrElse(i) { "0" }.toInt()
            when {
                segment1 > segment2 -> return 1
                segment1 < segment2 -> return -1
            }
        }
        return 0
    }
}