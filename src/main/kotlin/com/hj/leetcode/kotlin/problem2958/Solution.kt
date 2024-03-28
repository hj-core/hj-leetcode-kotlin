package com.hj.leetcode.kotlin.problem2958

import kotlin.math.max

/**
 * LeetCode page: [2958. Length of Longest Subarray With at Most K Frequency](https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun maxSubarrayLength(nums: IntArray, k: Int): Int {
        var result = 0
        var start = 0
        val counter = hashMapOf<Int, Int>() // number to its count

        for (end in nums.indices) {
            counter.compute(nums[end]) { _, count -> 1 + (count ?: 0) }

            while (counter[nums[end]]?.let { it > k } == true) {
                counter.compute(nums[start]) { _, count -> checkNotNull(count) - 1 }
                start += 1
            }
            result = max(result, end - start + 1)
        }
        return result
    }
}