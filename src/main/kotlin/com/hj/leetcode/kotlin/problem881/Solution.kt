package com.hj.leetcode.kotlin.problem881

/**
 * LeetCode page: [881. Boats to Save People](https://leetcode.com/problems/boats-to-save-people/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of people;
     */
    fun numRescueBoats(people: IntArray, limit: Int): Int {
        val sortedWeights = people.sorted()
        var left = 0
        var right = sortedWeights.lastIndex
        var result = 0

        while (left <= right) {
            if (sortedWeights[left] + sortedWeights[right] <= limit) {
                left++
            }
            right--
            result++
        }
        return result
    }
}