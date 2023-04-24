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
        val stoneGame = StoneGame().apply {
            offerStones(stones)
            playUntilEndOfGame()
        }
        return stoneGame.lastStoneWeight()
    }

    private class StoneGame {

        private val weightMaxPq = PriorityQueue<Int>(reverseOrder())
        private val weightIfNoStone = 0

        fun offerStones(stones: IntArray) {
            for (stone in stones) {
                weightMaxPq.offer(stone)
            }
        }

        fun playUntilEndOfGame() {
            while (weightMaxPq.size > 1) {
                chooseHeaviestTwoAndSmash()
            }
        }

        private fun chooseHeaviestTwoAndSmash() {
            val heaviest = weightMaxPq.poll()
            val secondHeaviest = weightMaxPq.poll()
            if (heaviest != secondHeaviest) {
                weightMaxPq.offer(heaviest - secondHeaviest)
            }
        }

        fun lastStoneWeight(): Int {
            check(weightMaxPq.size <= 1)
            return weightMaxPq.firstOrNull() ?: weightIfNoStone
        }
    }
}