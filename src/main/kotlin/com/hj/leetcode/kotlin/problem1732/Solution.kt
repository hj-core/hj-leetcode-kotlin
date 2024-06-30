package com.hj.leetcode.kotlin.problem1732

/**
 * LeetCode page: [1732. Find the Highest Altitude](https://leetcode.com/problems/find-the-highest-altitude/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of gain;
     */
    fun largestAltitude(gain: IntArray): Int {
        var result = 0
        var currentAltitude = 0
        for (altitudeGain in gain) {
            currentAltitude += altitudeGain
            result = maxOf(result, currentAltitude)
        }
        return result
    }
}