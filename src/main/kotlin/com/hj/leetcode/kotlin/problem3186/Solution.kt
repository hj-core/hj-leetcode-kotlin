package com.hj.leetcode.kotlin.problem3186

/**
 * LeetCode page: [3186. Maximum Total Damage With Spell Casting](https://leetcode.com/problems/maximum-total-damage-with-spell-casting/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of
    // power.
    fun maximumTotalDamage(power: IntArray): Long {
        val sortedPower = IntArray(power.size + 1)
        power.copyInto(sortedPower)
        sortedPower[power.size] = Int.MAX_VALUE // For easy boundary handling
        sortedPower.sort()

        // We work on a virtual sorted (power, freq) array,
        // and the DP is based on whether to take the new pair.
        // We only keep the four most recent sub results.
        val dp = LongArray(4)
        val dpPower = IntArray(4) { -3 }
        var size = 4 // index of the new power
        var freq = 1L // frequency of the new power

        for (i in 0..<sortedPower.lastIndex) {
            if (sortedPower[i] == sortedPower[i + 1]) {
                freq++
                continue
            }

            var j = size - 1
            while (dpPower[j and 3] + 2 >= sortedPower[i]) {
                j--
            }

            dp[size and 3] =
                maxOf(
                    dp[(size - 1) and 3],
                    freq * sortedPower[i] + dp[j and 3],
                )

            dpPower[size and 3] = sortedPower[i]
            size++
            freq = 1
        }

        return dp[(size - 1) and 3]
    }
}
