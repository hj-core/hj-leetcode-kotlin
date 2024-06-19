package com.hj.leetcode.kotlin.problem1482

/**
 * LeetCode page: [1482. Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogM) and Space O(1) where N is the size of bloomDay
     * and M is the maximum value of bloomDay;
     */
    fun minDays(bloomDay: IntArray, m: Int, k: Int): Int {
        if (bloomDay.size < m.toLong() * k) {
            return  -1
        }

        var low = 0
        var high = bloomDay.max()
        while (low < high) {
            val guess = (low + high) ushr 1
            if (maximumBouquets(bloomDay, guess, k) < m) {
                low = guess + 1
            } else {
                high = guess
            }
        }
        return high
    }

    private fun maximumBouquets(bloomDay: IntArray, duration: Int, k: Int): Int {
        var result = 0
        var bloomStart = 0
        while (bloomStart < bloomDay.size) {
            var bloomEnd = bloomStart
            while (bloomEnd < bloomDay.size && bloomDay[bloomEnd] <= duration) {
                bloomEnd++
            }
            result += (bloomEnd - bloomStart) / k
            bloomStart = bloomEnd + 1
        }
        return result
    }
}