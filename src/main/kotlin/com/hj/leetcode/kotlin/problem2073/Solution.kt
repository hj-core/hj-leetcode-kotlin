package com.hj.leetcode.kotlin.problem2073

import kotlin.math.min

/**
 * LeetCode page: [2073. Time Needed to Buy Tickets](https://leetcode.com/problems/time-needed-to-buy-tickets/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of tickets;
     */
    fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
        return tickets.foldIndexed(0) { index: Int, acc: Int, e: Int ->
            acc + min(e, if (index <= k) tickets[k] else tickets[k] - 1)
        }
    }
}