package com.hj.leetcode.kotlin.problem2558

import java.util.*
import kotlin.math.sqrt

/**
 * LeetCode page: [2558. Take Gifts From the Richest Pile](https://leetcode.com/problems/take-gifts-from-the-richest-pile/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+kLogN) and Space O(N) where N is the length of gifts.
     */
    fun pickGifts(
        gifts: IntArray,
        k: Int,
    ): Long {
        val pq = PriorityQueue<Int>(reverseOrder())
        for (gift in gifts) {
            pq.offer(gift)
        }

        repeat(k) {
            val gift = pq.poll()
            val left = sqrt(gift.toDouble()).toInt()
            pq.offer(left)
        }
        return pq.fold(0L) { acc, i -> acc + i }
    }
}
