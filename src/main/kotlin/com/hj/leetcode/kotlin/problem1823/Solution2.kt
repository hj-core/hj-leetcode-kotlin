package com.hj.leetcode.kotlin.problem1823

/**
 * LeetCode page: [1823. Find the Winner of the Circular Game](https://leetcode.com/problems/find-the-winner-of-the-circular-game/);
 */
class Solution2 {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun findTheWinner(n: Int, k: Int): Int {
        /* The idea is to start the game with zero-based indices and, after the
         * first removal, the remaining players form a game with size n-1 and
         * shifted indices.
         *
         * This recursive solution can be converted to an iterative solution with
         * O(1) space complexity.
         */
        return 1 + findTheWinnerZeroBased(n, k)
    }

    private fun findTheWinnerZeroBased(n: Int, k: Int): Int {
        if (n == 1) {
            return 0
        }
        return (findTheWinnerZeroBased(n - 1, k) + k) % n
    }
}