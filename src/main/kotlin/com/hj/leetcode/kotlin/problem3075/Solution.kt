package com.hj.leetcode.kotlin.problem3075

import java.util.*

/**
 * LeetCode page: [3075. Maximize Happiness of Selected Children](https://leetcode.com/problems/maximize-happiness-of-selected-children/);
 */
class Solution {
    // Complexity:
    // Time O(NLogk) and Space O(k) where N is the size of happiness.
    fun maximumHappinessSum(
        happiness: IntArray,
        k: Int,
    ): Long {
        val minPq = PriorityQueue<Int>()
        for (h in happiness) {
            minPq.offer(h)

            if (minPq.size > k) {
                minPq.poll()
            }
        }

        return (k - 1 downTo 0).sumOf {
            maxOf(0, minPq.poll() - it).toLong()
        }
    }
}
