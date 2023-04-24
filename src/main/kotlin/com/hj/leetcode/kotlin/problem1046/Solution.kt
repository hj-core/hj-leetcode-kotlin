package com.hj.leetcode.kotlin.problem1046

import java.util.*

/**
 * LeetCode page: [1046. Last Stone Weight](https://leetcode.com/problems/last-stone-weight/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of stones;
     */
    fun lastStoneWeight(stones: IntArray): Int {
        val stoneMaxPq = PriorityQueue<Int>(reverseOrder()).apply {
            offerStones(stones)
            playUntilEndOfGame()
        }
        val lastStone = stoneMaxPq.firstOrNull()
        return lastStone ?: 0
    }

    private fun PriorityQueue<Int>.offerStones(stones: IntArray) {
        for (stone in stones) {
            offer(stone)
        }
    }

    private fun PriorityQueue<Int>.playUntilEndOfGame() {
        while (size >= 2) {
            val heaviestStone = poll()
            val secondHeaviestStone = poll()
            if (heaviestStone != secondHeaviestStone) {
                offer(heaviestStone - secondHeaviestStone)
            }
        }
    }
}