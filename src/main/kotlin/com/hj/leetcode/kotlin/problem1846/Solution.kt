package com.hj.leetcode.kotlin.problem1846

/**
 * LeetCode page: [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of arr.
    fun maximumElementAfterDecrementingAndRearranging(arr: IntArray): Int {
        val numFreq = IntArray(arr.size + 1)
        for (num in arr) {
            if (num < arr.size) {
                numFreq[num] += 1
            } else {
                numFreq[arr.size] += 1
            }
        }

        var maxElement = 0
        for ((num, freq) in numFreq.withIndex()) {
            maxElement = minOf(maxElement + freq, num)
        }

        return maxElement
    }
}
