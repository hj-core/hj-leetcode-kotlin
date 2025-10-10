package com.hj.leetcode.kotlin.problem3494

/**
 * LeetCode page: [3494. Find the Minimum Amount of Time to Brew Potions](https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions/);
 */
class Solution2 {
    // Complexity:
    // Time O(NM) and Space O(N) where N and M are the length of
    // skill and mana, respectively.
    fun minTime(
        skill: IntArray,
        mana: IntArray,
    ): Long {
        val finishTime = LongArray(skill.size)
        for (m in mana) {
            finishTime[0] += m * skill[0]
            for (i in 1..<skill.size) {
                finishTime[i] = maxOf(finishTime[i - 1], finishTime[i]) + m * skill[i]
            }
            for (i in skill.size - 1 downTo 1) {
                finishTime[i - 1] = finishTime[i] - m * skill[i]
            }
        }
        return finishTime.last()
    }
}
