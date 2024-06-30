package com.hj.leetcode.kotlin.problem2971

/**
 * LeetCode page: [2971. Find Polygon With the Largest Perimeter](https://leetcode.com/problems/find-polygon-with-the-largest-perimeter/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun largestPerimeter(nums: IntArray): Long {
        var result = -1L
        val sorted = nums.sorted()
        var prefixSum = 0L
        for (num in sorted) {
            if (prefixSum > num) {
                result = prefixSum + num
            }
            prefixSum += num
        }
        return result
    }
}