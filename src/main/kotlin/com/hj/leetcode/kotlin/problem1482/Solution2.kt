package com.hj.leetcode.kotlin.problem1482

import java.util.*

class Solution2 {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of bloomDay;
     */
    fun minDays(bloomDay: IntArray, m: Int, k: Int): Int {
        val requiredFlowers = m.toLong() * k
        when {
            bloomDay.size.toLong() < requiredFlowers -> return -1
            bloomDay.size.toLong() == requiredFlowers -> return bloomDay.max()
        }

        val sortedIndices = bloomDay
            .indices
            .sortedBy { -bloomDay[it] }
        val dismissedIndices = TreeSet<Int>()
        var maxBouquets = bloomDay.size / k

        for (index in sortedIndices) {
            val left = dismissedIndices.floor(index) ?: -1
            val right = dismissedIndices.ceiling(index) ?: bloomDay.size

            maxBouquets -= ((right - left - 1) / k
                    - (right - index - 1) / k - (index - left - 1) / k)
            if (maxBouquets < m) {
                return bloomDay[index]
            }
            dismissedIndices.add(index)
        }
        throw IllegalStateException()
    }
}