package com.hj.leetcode.kotlin.problem2461

/**
 * LeetCode page: [2461. Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(k) where N is the length of nums.
     */
    fun maximumSubarraySum(
        nums: IntArray,
        k: Int,
    ): Long {
        var windowSum = 0L
        val numFreq = mutableMapOf<Int, Int>()
        // First window whose end is k-1
        for (i in 0..<k) {
            val num = nums[i]
            windowSum += num
            numFreq[num] = (numFreq[num] ?: 0) + 1
        }
        var result = if (numFreq.size == k) windowSum else 0L
        // Slide window
        for (end in k..<nums.size) {
            val enter = nums[end]
            val leave = nums[end - k]
            windowSum += enter - leave
            numFreq[enter] = (numFreq[enter] ?: 0) + 1
            numFreq[leave] = (numFreq[leave] ?: 0) - 1
            if (numFreq[leave] == 0) {
                numFreq.remove(leave)
            }
            if (numFreq.size == k && result < windowSum) {
                result = windowSum
            }
        }
        return result
    }
}
