package com.hj.leetcode.kotlin.problem1962

import java.util.*

/**
 * LeetCode page: [1962. Remove Stones to Minimize the Total](https://leetcode.com/problems/remove-stones-to-minimize-the-total/);
 */
class Solution {
    /* Complexity:
     * Time O((N+k)LogN) and Space O(N) where N is the size of piles;
     */
    fun minStoneSum(piles: IntArray, k: Int): Int {
        val pilesPq = PriorityQueue(reverseOrder<Int>())
        for (pile in piles) {
            pilesPq.offer(pile)
        }

        repeat(k) {
            val maxPile = pilesPq.poll()
            pilesPq.offer(maxPile - maxPile / 2)
        }
        return pilesPq.sum()
    }
}