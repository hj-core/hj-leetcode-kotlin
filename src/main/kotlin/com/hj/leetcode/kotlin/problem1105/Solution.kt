package com.hj.leetcode.kotlin.problem1105

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1105. Filling Bookcase Shelves](https://leetcode.com/problems/filling-bookcase-shelves/);
 */
class Solution {
    /* Complexity:
     * Time O(N * shelfWidth) and Space O(N) where N is the size of books;
     */
    fun minHeightShelves(books: Array<IntArray>, shelfWidth: Int): Int {
        // dp[i]::= minHeightShelves(books[i:], shelfWidth)
        val dp = IntArray(books.size + 1) { Int.MAX_VALUE }

        dp[books.size] = 0
        for (i in books.lastIndex downTo 0) {
            /* Try all the possible breakpoints for the first shelf to find
             * the minimum overall height.
             */
            var thickness = 0
            var height = 0
            for (breakpoint in i..<books.size) {
                thickness += books[breakpoint][0]
                if (thickness > shelfWidth) {
                    break
                }

                height = max(height, books[breakpoint][1])
                dp[i] = min(dp[i], height + dp[breakpoint + 1])
            }
        }
        return dp[0]
    }
}