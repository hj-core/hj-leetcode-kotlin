package com.hj.leetcode.kotlin.problem2975

import kotlin.math.abs

/**
 * LeetCode page: [2975. Maximum Square Area by Removing Fences From a Field](https://leetcode.com/problems/maximum-square-area-by-removing-fences-from-a-field/);
 */
class Solution {
    val modulus = 1_000_000_007

    // Complexity:
    // Time O(H^2+V^2) and Space O(min(H,V)), where H and V are the
    // length of hFences and vFences respectively.
    fun maximizeSquareArea(
        m: Int,
        n: Int,
        hFences: IntArray,
        vFences: IntArray,
    ): Int {
        if (hFences.size > vFences.size) {
            return maximizeSquareArea(n, m, vFences, hFences)
        }

        val spacings = hashSetOf(m - 1)
        for ((i, bar) in hFences.withIndex()) {
            spacings.add(bar - 1)
            spacings.add(m - bar)
            for (j in 0..<i) {
                spacings.add(abs(bar - hFences[j]))
            }
        }

        if (n - 1 in spacings) {
            return area(n - 1)
        }

        var maxWidth = 0
        for ((i, bar) in vFences.withIndex()) {
            if (bar - 1 in spacings) {
                maxWidth = maxOf(maxWidth, bar - 1)
            }
            if (n - bar in spacings) {
                maxWidth = maxOf(maxWidth, n - bar)
            }
            for (j in 0..<i) {
                val width = abs(bar - vFences[j])
                if (width in spacings) {
                    maxWidth = maxOf(maxWidth, width)
                }
            }
        }

        return if (maxWidth == 0) -1 else area(maxWidth)
    }

    // Returns width * width % this.modulus.
    private fun area(width: Int): Int =
        width
            .toLong()
            .let { it * it % modulus }
            .toInt()
}
