package com.hj.leetcode.kotlin.problem3005

/**
 * LeetCode page: [3005. Count Elements With Maximum Frequency](https://leetcode.com/problems/count-elements-with-maximum-frequency/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun maxFrequencyElements(nums: IntArray): Int {
        val numFreq = nums.asList().groupingBy { it }.eachCount()
        val maxFreq = numFreq.values.max()
        return maxFreq * numFreq.values.count { it == maxFreq }
    }
}