package com.hj.leetcode.kotlin.problem1512

/**
 * LeetCode page: [1512. Number of Good Pairs](https://leetcode.com/problems/number-of-good-pairs/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun numIdenticalPairs(nums: IntArray): Int {
        val numFrequencies = nums.asSequence().groupingBy { it }.eachCount()
        var result = 0
        for ((_, frequency) in numFrequencies) {
            if (frequency >= 2) {
                result += frequency * (frequency - 1) / 2
            }
        }
        return result
    }
}