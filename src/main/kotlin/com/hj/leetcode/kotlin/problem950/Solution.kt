package com.hj.leetcode.kotlin.problem950

/**
 * LeetCode page: [950. Reveal Cards In Increasing Order](https://leetcode.com/problems/reveal-cards-in-increasing-order/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of deck;
     */
    fun deckRevealedIncreasing(deck: IntArray): IntArray {
        val sorted = deck.sorted()
        val result = ArrayDeque<Int>().apply {
            addLast(sorted.last())
        }

        for (i in sorted.lastIndex - 1 downTo 0) {
            result.addFirst(result.removeLast())
            result.addFirst(sorted[i])
        }
        return result.toIntArray()
    }
}