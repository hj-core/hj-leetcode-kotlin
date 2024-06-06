package com.hj.leetcode.kotlin.problem846

/**
 * LeetCode page: [846. Hand of Straights](https://leetcode.com/problems/hand-of-straights/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of hand;
     */
    fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
        val countCards = hand.asIterable()
            .groupingBy { it }
            .eachCountTo(hashMapOf())

        for (card in hand) {
            if (card !in countCards) {
                continue
            }

            /* If we have a card of value t and there is no card with value t-1,
             * then this card must be the start of sequences.
             */
            var sequenceStart = card
            while (sequenceStart - 1 in countCards) {
                sequenceStart--
            }

            while (sequenceStart <= card) {
                if (sequenceStart !in countCards) {
                    sequenceStart++
                    continue
                }

                val countStart = checkNotNull(countCards[sequenceStart])
                for (tailCard in sequenceStart + 1..<sequenceStart + groupSize) {
                    val countTail = countCards[tailCard]
                    if (countTail == null || countTail < countStart) {
                        return false
                    }
                    if (countTail == countStart) {
                        countCards.remove(tailCard)
                    } else {
                        countCards[tailCard] = countTail - countStart
                    }
                }

                countCards.remove(sequenceStart)
                sequenceStart++
            }
        }
        return true
    }
}