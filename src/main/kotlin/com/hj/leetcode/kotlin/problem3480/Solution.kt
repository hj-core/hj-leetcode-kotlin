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
        // extension for each index.
        val constraints = Array(n + 2) { intArrayOf(n + 1, n + 1) }
        for (pair in conflictingPairs) {
            val a = pair.min()
            var b = pair.max()
            if (constraints[a][0] > b) {
                constraints[a][0] = b.also { b = constraints[a][0] }
            }
            if (constraints[a][1] > b) {
                constraints[a][1] = b
            }
        }

        var rawCount = 0L
        var maxIncrease = 0L
        var i = n
        for (j in n downTo 1) {
            constraints[j][0] = minOf(constraints[j][0], constraints[j + 1][0])
            constraints[j][1] = minOf(constraints[j][1], constraints[j + 1][0])

            rawCount += constraints[j][0] - j
            if (constraints[j][0] == constraints[j][1]) {
                continue
            }

            // Compute the increase in subarray count if we get
            // rid of constraints[j][0].
            var increase = 0L
            i = minOf(i, j - 1)
            while (i > 0 && constraints[i][0] > constraints[j][1]) {
                i--
            }
            increase += (j - i).toLong() * (constraints[j][1] - constraints[j][0])

            var iConstraint = constraints[i][0] // constraint at i if constraint[j][0] is removed
            while (i > 0 && iConstraint > constraints[j][0]) {
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
