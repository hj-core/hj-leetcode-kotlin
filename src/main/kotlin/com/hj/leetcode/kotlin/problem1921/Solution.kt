package com.hj.leetcode.kotlin.problem1921

import kotlin.math.ceil

/**
 * LeetCode page: [1921. Eliminate Maximum Number of Monsters](https://leetcode.com/problems/eliminate-maximum-number-of-monsters/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of dist;
     */
    fun eliminateMaximum(dist: IntArray, speed: IntArray): Int {
        val arrivalTimes = IntArray(dist.size) { index ->
            ceil(dist[index].toDouble() / speed[index]).toInt()
        }
        arrivalTimes.sort()

        for (index in arrivalTimes.indices) {
            val numArrived = index + 1
            val maxEliminated = arrivalTimes[index]

            if (maxEliminated < numArrived) {
                return maxEliminated
            }
        }
        return arrivalTimes.size
    }
}