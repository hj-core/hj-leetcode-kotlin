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
        val sortedP = players.sortedArray()
        val sortedT = trainers.sortedArray()
        val j0 = maxOf(0, trainers.size - players.size)
        return (j0..<trainers.size).fold(0) { result, j ->
            if (sortedP[result] <= sortedT[j]) result + 1 else result
        }
    }
}
