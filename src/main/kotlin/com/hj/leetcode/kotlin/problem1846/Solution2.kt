package com.hj.leetcode.kotlin.problem1846

import kotlin.math.min

/**
 * LeetCode page: [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of arr;
     */
    fun maximumElementAfterDecrementingAndRearranging(arr: IntArray): Int {
        val n = arr.size
        val range = 1..n

        val rangeCount = IntArray(n + 1)
        for (num in arr) {
            if (num in range) {
                rangeCount[num]++
            }
        }

        val totalRangeCount = rangeCount.sum()
        if (totalRangeCount == 0) {
            return n
        }

        var result = 0
        for (num in range) {
            if (rangeCount[num] == 0) {
                continue
            }
            result = min(result + rangeCount[num], num)
        }
        result += n - totalRangeCount
        return result
    }
}