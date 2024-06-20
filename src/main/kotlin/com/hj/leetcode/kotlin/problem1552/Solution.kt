package com.hj.leetcode.kotlin.problem1552

/**
 * LeetCode page: [1552. Magnetic Force Between Two Balls](https://leetcode.com/problems/magnetic-force-between-two-balls/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+NLogM) and Space O(N) where N is the size of position
     * and M is the maximum value in position;
     */
    fun maxDistance(position: IntArray, m: Int): Int {
        val sortedPosition = position.sorted()
        var low = 1
        var high = position.max() / (m - 1)
        while (low <= high) {
            val guess = (low + high) ushr 1
            if (maxBalls(sortedPosition, guess) >= m) {
                low = guess + 1
            } else {
                high = guess - 1
            }
        }
        return high
    }

     private fun maxBalls(sortedPosition: List<Int>, minSpacing: Int): Int {
         var lastBall = sortedPosition[0]
         var result = 1
         for (index in 1..<sortedPosition.size) {
             if (sortedPosition[index] - lastBall >= minSpacing) {
                 lastBall = sortedPosition[index]
                 result++
             }
         }
         return result
     }
}