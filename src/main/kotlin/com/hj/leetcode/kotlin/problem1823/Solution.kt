package com.hj.leetcode.kotlin.problem1823

/**
 * LeetCode page: [1823. Find the Winner of the Circular Game](https://leetcode.com/problems/find-the-winner-of-the-circular-game/);
 */
class Solution {
    /* Complexity:
     * Time O(nk) and Space O(n);
     */
    fun findTheWinner(n: Int, k: Int): Int {
        if (k == 1) {
            return n
        }

        val friends = ArrayDeque<Int>().apply {
            addAll(1..n)
        }

        while (friends.size > 1) {
            repeat(k - 1) {
                friends.addLast(friends.removeFirst())
            }
            friends.removeFirst()
        }
        return friends.first()
    }
}