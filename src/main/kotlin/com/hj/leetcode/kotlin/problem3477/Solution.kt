package com.hj.leetcode.kotlin.problem3477

/**
 * LeetCode page: [3477. Fruits Into Baskets II](https://leetcode.com/problems/fruits-into-baskets-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the size of
    // fruits and baskets.
    fun numOfUnplacedFruits(
        fruits: IntArray,
        baskets: IntArray,
    ): Int {
        val used = BooleanArray(baskets.size)
        var result = 0

        for (fruit in fruits) {
            var unplaced = true
            for (i in baskets.indices) {
                if (!used[i] && fruit <= baskets[i]) {
                    used[i] = true
                    unplaced = false
                    break
                }
            }

            if (unplaced) {
                result++
            }
        }
        return result
    }
}
