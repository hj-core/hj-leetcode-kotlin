package com.hj.leetcode.kotlin.problem2333

import kotlin.math.abs

/**
 * LeetCode page: [2333. Minimum Sum of Squared Difference](https://leetcode.com/problems/minimum-sum-of-squared-difference/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the size of nums1 and nums2.
    fun minSumSquareDiff(
        nums1: IntArray,
        nums2: IntArray,
        k1: Int,
        k2: Int,
    ): Long {
        val n = nums1.size
        val diff = IntArray(n) { abs(nums1[it] - nums2[it]) }
        diff.sort()

        // greedily push i if we can reduce values in diff[i..] to diff[i]
        var quota = k1 + k2.toLong()
        var i = n - 1
        while (0 < i) {
            val cost = stepCost(diff, i)
            if (quota < cost) {
                break
            }
            quota -= cost
            i--
        }

        var sum = (0..<i).sumOf { square(diff[it].toLong()) }
        val tailSize = n - i
        val furtherDown = quota / tailSize
        val extraSize = quota % tailSize
        sum += square((diff[i] - furtherDown - 1).coerceAtLeast(0L)) * extraSize
        sum += square((diff[i] - furtherDown).coerceAtLeast(0)) * (tailSize - extraSize)
        return sum
    }

    private fun stepCost(
        diff: IntArray,
        i: Int,
    ): Long = (diff[i] - diff[i - 1]) * (diff.size - i).toLong()

    private fun square(x: Long): Long = x * x
}
