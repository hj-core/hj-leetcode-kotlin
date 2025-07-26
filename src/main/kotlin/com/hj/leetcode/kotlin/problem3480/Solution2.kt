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
        // The most and second most restricted constraints of
        // extension, i.e., a subarray starting at index i cannot
        // extend to or beyond constraints[i], for each index.
        val constraints = Array(n + 2) { intArrayOf(n + 1, n + 1) }

        constraints[0][0] = 0
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

        // The accumulating increase if we remove the current
        // dominant constraint.
        var increase = 0L
        var rawCount = 0L
        var maxIncrease = 0L

        for (j in n downTo 0) {
            // If we encounter a new dominant constraint, the effect
            // of removing the previous dominant one has terminated.
            // We reset and accumulate the increase for this new dominant
            // constraint.
            if (constraints[j][0] <= constraints[j + 1][0]) {
                maxIncrease = maxOf(maxIncrease, increase)
                increase = 0
                constraints[j][1] = minOf(constraints[j][1], constraints[j + 1][0])
            } else {
                constraints[j][1] = minOf(constraints[j][0], constraints[j + 1][1])
                constraints[j][0] = constraints[j + 1][0]
            }

            rawCount += constraints[j][0] - j
            increase += constraints[j][1] - constraints[j][0]
        }
        return rawCount + maxIncrease
    }
}
