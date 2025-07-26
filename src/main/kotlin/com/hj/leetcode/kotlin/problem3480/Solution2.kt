package com.hj.leetcode.kotlin.problem3480

/**
 * LeetCode page: [3480. Maximize Subarrays After Removing One Conflicting Pair](https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair/);
 */
class Solution2 {
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
        constraints[0][0] = 0

        var rawCount = 0L
        var maxIncrease = 0L
        // The most restricted constraint at index i, and the
        // second most restricted constraint if we delete the
        // most restrictive one.
        var minC = n + 1
        var secondMinC = n + 1
        // The accumulated increase if we delete the minC
        var increase = 0L

        for (j in n downTo 0) {
            // If we encounter constraints[i][0] <= minC, the
            // effect of removing minC has terminated. We update
            // maxIncrease and start a new counter for this new
            // dominant constraints.
            if (constraints[j][0] <= minC) {
                maxIncrease = maxOf(maxIncrease, increase)
                increase = 0
                secondMinC = minOf(minC, constraints[j][1])
                minC = constraints[j][0]
            } else {
                secondMinC = minOf(secondMinC, constraints[j][0])
            }

            rawCount += minC - j
            increase += secondMinC - minC
        }
        return rawCount + maxIncrease
    }
}
