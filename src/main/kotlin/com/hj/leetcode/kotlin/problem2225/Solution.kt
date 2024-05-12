package com.hj.leetcode.kotlin.problem2225

/**
 * LeetCode page: [2225. Find Players With Zero or One Losses](https://leetcode.com/problems/find-players-with-zero-or-one-losses/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of matches;
     */
    fun findWinners(matches: Array<IntArray>): List<List<Int>> {
        val playerLosses = hashMapOf<Int, Int>()
        for ((winner, loser) in matches) {
            playerLosses.putIfAbsent(winner, 0)
            playerLosses[loser] = 1 + (playerLosses[loser] ?: 0)
        }

        val result = listOf(mutableListOf(), mutableListOf<Int>())
        for ((player, losses) in playerLosses) {
            when (losses) {
                0 -> result[0].add(player)
                1 -> result[1].add(player)
            }
        }
        result.forEach { it.sort() }
        return result
    }
}