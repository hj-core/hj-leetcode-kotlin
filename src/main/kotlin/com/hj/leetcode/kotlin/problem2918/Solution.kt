package com.hj.leetcode.kotlin.problem2918

/**
 * LeetCode page: [2918. Minimum Equal Sum of Two Arrays After Replacing Zeros](https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(1) where N and M are the length of nums1
    // and nums2, respectively.
    fun minSum(
        nums1: IntArray,
        nums2: IntArray,
    ): Long {
        val sum1 = nums1.fold(0L) { acc, i -> acc + i }
        val zeros1 = nums1.count { it == 0 }

        val sum2 = nums2.fold(0L) { acc, i -> acc + i }
        val zeros2 = nums2.count { it == 0 }

        return when {
            zeros1 == 0 && zeros2 == 0 && sum1 != sum2 -> -1
            zeros1 == 0 && zeros2 != 0 && sum1 - sum2 < zeros2 -> -1
            zeros1 != 0 && zeros2 == 0 && sum2 - sum1 < zeros1 -> -1
            else -> maxOf(sum1 + zeros1, sum2 + zeros2)
        }
    }
}
