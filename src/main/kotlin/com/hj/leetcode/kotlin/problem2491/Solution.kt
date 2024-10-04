package com.hj.leetcode.kotlin.problem2491

/**
 * LeetCode page: [2491. Divide Players Into Teams of Equal Skill](https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of skill.
     */
    fun dividePlayers(skill: IntArray): Long {
        if (skill.size == 2) {
            return (skill[0] * skill[1]).toLong()
        }
        val sum = skill.sum()
        val n = skill.size
        if (sum % (n / 2) != 0) {
            return -1
        }
        val target = sum / (n / 2)
        val pending = mutableMapOf<Int, Int>() // skill to count
        var paired = 0
        var result = 0L
        for (skl in skill) {
            val complement = target - skl
            if (complement < 1) {
                return -1
            }
            val countComplement = pending[complement] ?: 0
            if (countComplement > 0) {
                paired += 2
                pending[complement] = countComplement - 1
                result += skl * complement
            } else {
                pending.compute(skl) { _, v -> (v ?: 0) + 1 }
            }
        }
        return if (paired == n) result else -1
    }
}
