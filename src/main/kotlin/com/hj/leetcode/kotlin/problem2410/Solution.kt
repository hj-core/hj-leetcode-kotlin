package com.hj.leetcode.kotlin.problem2410

/**
 * LeetCode page: [2410. Maximum Matching of Players With Trainers](https://leetcode.com/problems/maximum-matching-of-players-with-trainers/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN+MLogM) and Space O(N+M) where N is the length of
    // players and M is the length of trainers.
    fun matchPlayersAndTrainers(
        players: IntArray,
        trainers: IntArray,
    ): Int {
        val sortedPlayers = players.sortedArray()
        val sortedTrainers = trainers.sortedArray()

        var result = 0
        var i = 0
        var j = maxOf(0, trainers.size - players.size)
        while (i < players.size && j < trainers.size) {
            if (sortedPlayers[i] <= sortedTrainers[j]) {
                i++
                result++
            }
            j++
        }
        return result
    }
}
