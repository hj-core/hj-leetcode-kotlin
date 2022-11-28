package com.hj.leetcode.kotlin.problem2225

/**
 * LeetCode page: [2225. Find Players With Zero or One Losses](https://leetcode.com/problems/find-players-with-zero-or-one-losses/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of matches;
     */
    fun findWinners(matches: Array<IntArray>): List<List<Int>> {
        val numLostMatchesPerPlayer = hashMapOf<Int, Int>()

        for ((winner, loser) in matches) {
            numLostMatchesPerPlayer.let {
                it.putIfAbsent(winner, 0)
                it[loser] = it.getOrDefault(loser, 0) + 1
            }
        }

        val playerNoLostMatch = mutableListOf<Int>()
        val playerLostOneMatch = mutableListOf<Int>()

        for ((player, numLosses) in numLostMatchesPerPlayer) {
            when (numLosses) {
                0 -> playerNoLostMatch.add(player)
                1 -> playerLostOneMatch.add(player)
            }
        }

        playerNoLostMatch.sort()
        playerLostOneMatch.sort()
        return listOf(playerNoLostMatch, playerLostOneMatch)
    }
}