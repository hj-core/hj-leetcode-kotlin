package com.hj.leetcode.kotlin.problem2279

/**
 * LeetCode page: [2279. Maximum Bags With Full Capacity of Rocks](https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of capacity/rocks;
     */
    fun maximumBags(capacity: IntArray, rocks: IntArray, additionalRocks: Int): Int {
        val sortedEmptySpaces = IntArray(capacity.size) { capacity[it] - rocks[it] }.apply { sort() }
        var remainingRocks = additionalRocks
        for ((index, space) in sortedEmptySpaces.withIndex()) {
            if (remainingRocks < space) {
                return index
            }
            remainingRocks -= space
        }
        return sortedEmptySpaces.size
    }
}