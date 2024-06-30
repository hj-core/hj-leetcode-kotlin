package com.hj.leetcode.kotlin.problem3075

import java.util.*

/**
 * LeetCode page: [3075. Maximize Happiness of Selected Children](https://leetcode.com/problems/maximize-happiness-of-selected-children/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogk) and Space O(k) where N is the size of happiness;
     */
    fun maximumHappinessSum(happiness: IntArray, k: Int): Long {
        return happiness
            .kHappiest(k)
            .sumWithReduction();
    }

    private fun IntArray.kHappiest(k: Int): List<Int> {
        val pq = PriorityQueue<Int>()
        for (happiness in this) {
            pq.offer(happiness)
            if (pq.size > k) {
                pq.poll()
            }
        }

        return buildList {
            repeat(pq.size) { add(pq.poll()) }
        }.asReversed()
    }

    private fun List<Int>.sumWithReduction(): Long {
        var result = 0L
        for ((reduction, happiness) in this.withIndex()) {
            result += (happiness - reduction).coerceAtLeast(0)
        }
        return result
    }
}