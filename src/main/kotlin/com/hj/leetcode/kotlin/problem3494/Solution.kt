package com.hj.leetcode.kotlin.problem3494

/**
 * LeetCode page: [3494. Find the Minimum Amount of Time to Brew Potions](https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions/);
 */
class Solution {
    // Complexity:
    // Time O(NM) and Space O(1) where N and M are the length of
    // skill and mana, respectively.
    fun minTime(
        skill: IntArray,
        mana: IntArray,
    ): Long {
        // S(i, j):= S(i, 0) + mana[i] * sum(skill[0..<j])
        // E(i, j):= S(i, 0) + mana[i] * sum(skill[0..=j])
        //
        // S(i, j) and E(i, j) are the start time and complete
        // time of the jth wizard making the ith potion.
        //
        // We can find the minimum S(i+1, 0) - S(i, 0) by
        // satisfying S(i+1, j) >= S(i, j) for all j.
        var t = 0L
        var skillSum = 0
        for (i in 0..<mana.size - 1) {
            val m = mana[i].toLong()
            val dm = mana[i + 1] - m
            var dt = m * skill[0] // j=0
            skillSum = 0 // sum(skill[0..<j])

            for (j in 1..<skill.size) {
                skillSum += skill[j - 1]
                dt = maxOf(dt, m * skill[j] - dm * skillSum)
            }
            t += dt
        }

        if (mana.size > 1) {
            skillSum += skill.last()
        } else {
            skillSum = skill.sum()
        }
        t += mana.last().toLong() * skillSum
        return t
    }
}
