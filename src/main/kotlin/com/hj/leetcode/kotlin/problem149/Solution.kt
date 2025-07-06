package com.hj.leetcode.kotlin.problem149

import kotlin.math.abs

/**
 * LeetCode page: [149. Max Points on a Line](https://leetcode.com/problems/max-points-on-a-line/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of points.
    fun maxPoints(points: Array<IntArray>): Int {
        var result = 1
        for ((i, point) in points.withIndex()) {
            val maxSlopeFreq =
                (i + 1..<points.size)
                    .groupingBy { Slope.fromPoints(point, points[it]) }
                    .eachCount()
                    .values
                    .maxOrNull() ?: 0
            result = maxOf(result, maxSlopeFreq + 1)
        }
        return result
    }
}

/**
 * A class representing the slope in its irreducible form. There are
 * some special cases:
 * - (0, 0, 1) for a single point.
 * - (0, 1, 1) for horizontal lines.
 * - (1, 0, 1) for vertical lines.
 */
private data class Slope(
    val numerator: Int,
    val denominator: Int,
    val sign: Int,
) {
    companion object {
        fun fromPoints(
            point1: IntArray,
            point2: IntArray,
        ): Slope {
            val dy = point1[1] - point2[1]
            val dx = point1[0] - point2[0]

            if (dy == 0 && dx == 0) {
                return Slope(0, 0, 1)
            }
            if (dy == 0) {
                return Slope(0, 1, 1)
            }
            if (dx == 0) {
                return Slope(1, 0, 1)
            }

            val absNumerator = abs(dy)
            val absDenominator = abs(dx)
            val gcd = gcd(absNumerator, absDenominator)
            val sign = if ((dy < 0 && dx > 0) || (dy > 0 && dx < 0)) -1 else 1
            return Slope(absNumerator / gcd, absDenominator / gcd, sign)
        }
    }
}

private tailrec fun gcd(
    a: Int,
    b: Int,
): Int {
    if (b == 0) {
        return a
    }
    return gcd(b, a % b)
}
