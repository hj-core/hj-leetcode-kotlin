package com.hj.leetcode.kotlin.problem2491

/**
 * LeetCode page: [2491. Divide Players Into Teams of Equal Skill](https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of skill.
     */
    fun dividePlayers(skill: IntArray): Long {
        val n = skill.size
        if (n == 2) {
            return (skill[0] * skill[1]).toLong()
        }
        val totalSkills = skill.sum()
        if (totalSkills % (n / 2) != 0) {
            return -1
        }
        val teamSkills = totalSkills / (n / 2)
        val pendingPlayers = mutableMapOf<Int, Int>() // skill to count
        var pairedPlayers = 0
        var result = 0L
        for (skl in skill) {
            val complement = teamSkills - skl
            if (complement < 1) {
                return -1
            }
            val numComplement = pendingPlayers[complement] ?: 0
            if (numComplement > 0) {
                pairedPlayers += 2
                pendingPlayers[complement] = numComplement - 1
                result += skl * complement
            } else {
                pendingPlayers.compute(skl) { _, v -> (v ?: 0) + 1 }
            }
        }
        return if (pairedPlayers == n) result else -1
    }
}
