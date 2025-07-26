package com.hj.leetcode.kotlin.problem3480

/**
 * LeetCode page: [3480. Maximize Subarrays After Removing One Conflicting Pair](https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Space O(n).
    fun maxSubarrays(
        n: Int,
        conflictingPairs: Array<IntArray>,
    ): Long {
        // We collect the two most restricted constraints of
        // extension, i.e., a subarray starting at index i cannot
        // extend to or beyond constraints[i], for each index.
        val constraints = Array(n + 2) { intArrayOf(n + 1, n + 1) }
        for (pair in conflictingPairs) {
            val a = pair.min()
            val b = pair.max()
            if (b < constraints[a][0]) {
                constraints[a][1] = constraints[a][0]
                constraints[a][0] = b
            } else if (b < constraints[a][1]) {
                constraints[a][1] = b
            }
        }
        constraints[0][0] = -1

        // The first index that isn't affected by the removal of
        // constraints[j][0]. It is non-ascending as j decreases.
        var i = n
        var rawCount = 0L
        var maxIncrease = 0L
        for (j in n downTo 1) {
            // The constraints at j will be affected by the indices afterward
            constraints[j][0] = minOf(constraints[j][0], constraints[j + 1][0])
            constraints[j][1] = minOf(constraints[j][1], constraints[j + 1][0])

            rawCount += constraints[j][0] - j
            if (constraints[j][0] == constraints[j][1]) {
                continue // Removing constraints[j][0] does not help
            }

            // Compute the increase in subarray count if we get rid
            // of constraints[j][0].
            var increase = 0L
            i = minOf(i, j - 1)

            // For index i with the most restrictive constraint larger
            // than constraints[j][1], the increase count is simply
            // constraints[j][1] - constraints[j][0].
            while (constraints[i][0] > constraints[j][1]) {
                i--
            }
            increase += (j - i).toLong() * (constraints[j][1] - constraints[j][0])

            // For index i with the most restrictive constraint larger
            // than constraints[j][0] but smaller than constraints[j][1],
            // the increased count is iConstraint - constraints[j][0].
            var iConstraint = constraints[i][0]
            while (iConstraint > constraints[j][0]) {
                increase += iConstraint - constraints[j][0]
                i--
                iConstraint = minOf(iConstraint, constraints[i][0])
            }

            if (increase > maxIncrease) {
                maxIncrease = increase
            }
        }
        return rawCount + maxIncrease
    }
}
