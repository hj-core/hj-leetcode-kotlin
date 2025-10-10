package com.hj.leetcode.kotlin.problem3005

/**
 * LeetCode page: [3005. Count Elements With Maximum Frequency](https://leetcode.com/problems/count-elements-with-maximum-frequency/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(M) where N is the length of
    // nums and M is the maximum possible number.
    fun maxFrequencyElements(nums: IntArray): Int {
        val freqs = IntArray(101)
        var maxFreq = 0
        var count = 0

        for (elem in nums) {
            freqs[elem]++
            if (freqs[elem] > maxFreq) {
                maxFreq = freqs[elem]
                count = 1
            } else if (freqs[elem] == maxFreq) {
                count++
            }
        }
        return maxFreq * count
    }
}
