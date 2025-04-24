package com.hj.leetcode.kotlin.problem2799

/**
 * LeetCode page: [2799. Count Complete Subarrays in an Array](https://leetcode.com/problems/count-complete-subarrays-in-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun countCompleteSubarrays(nums: IntArray): Int {
        val numFreq = mutableMapOf<Int, Int>()
        for (num in nums) {
            numFreq[num] = 0
        }

        // We consider the subarray in the range [start, minEnd) and its
        // shortage of distinct numbers.
        var result = 0
        var minEnd = 0
        var shortage = numFreq.size

        for (start in nums.indices) {
            while (minEnd < nums.size && 0 < shortage) {
                numFreq[nums[minEnd]] = (numFreq[nums[minEnd]] ?: 0) + 1
                if (numFreq[nums[minEnd]] == 1) {
                    shortage--
                }
                minEnd++
            }
            if (0 < shortage) {
                break
            }

            result += nums.size - minEnd + 1
            numFreq[nums[start]] = (numFreq[nums[start]] ?: 0) - 1
            if (numFreq[nums[start]] == 0) {
                shortage++
            }
        }
        return result
    }
}
