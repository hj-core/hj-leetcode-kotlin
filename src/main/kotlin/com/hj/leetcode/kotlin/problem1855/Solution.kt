package com.hj.leetcode.kotlin.problem1855

/**
 * LeetCode page: [1855. Maximum Distance Between a Pair of Values](https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums2.
    fun maxDistance(
        nums1: IntArray,
        nums2: IntArray,
    ): Int {
        var i1 = 0
        for (i2 in nums2.indices) {
            if (i1 == nums1.size) {
                return i2 - i1
            }
            if (nums2[i2] < nums1[i1]) {
                i1++
            }
        }

        return (nums2.lastIndex - i1).coerceAtLeast(0)
    }
}
