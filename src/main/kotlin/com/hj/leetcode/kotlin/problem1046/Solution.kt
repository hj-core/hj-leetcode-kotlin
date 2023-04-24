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
        val stoneMaxPq = StoneMaxPq().apply {
            offerStones(stones)
            playUntilEndOfGame()
        }
        val lastStoneWeight = stoneMaxPq.firstOrNull
        return lastStoneWeight ?: 0
    }

    private class StoneMaxPq {

        private val maxPq = PriorityQueue<Int>(reverseOrder())
        val firstOrNull get() = maxPq.firstOrNull()

        fun offerStones(stones: IntArray) {
            for (stone in stones) {
                maxPq.offer(stone)
            }
        }

        fun playUntilEndOfGame() {
            while (maxPq.size >= 2) {
                chooseHeaviestTwoAndSmash()
            }
        }

        private fun chooseHeaviestTwoAndSmash() {
            val heaviest = maxPq.poll()
            val secondHeaviest = maxPq.poll()
            if (heaviest != secondHeaviest) {
                maxPq.offer(heaviest - secondHeaviest)
            }
        }
    }
}