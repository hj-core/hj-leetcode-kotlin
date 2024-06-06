package com.hj.leetcode.kotlin.problem846

/**
 * LeetCode page: [846. Hand of Straights](https://leetcode.com/problems/hand-of-straights/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of hand;
     */
    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
        val countCards = hand
            .asIterable()
            .groupingBy { it }
            .eachCountTo(hashMapOf())

        for (startCard in countCards.keys.sorted()) {
            val countStart = checkNotNull(countCards[startCard])
            if (countStart == 0) {
                continue
            }

            /* We are removing cards through this for-loop.
             * Since we can remove at most hand.size cards, the overall time complexity
             * for the two for-loops are O(hand.size).
             */
            for (card in startCard + 1..< startCard + groupSize) {
                val countCard = countCards[card]
                if (countCard == null || countCard < countStart) {
                    return false
                }
                countCards[card] = countCard - countStart
            }
        }
        return true
    }
}