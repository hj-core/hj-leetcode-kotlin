package com.hj.leetcode.kotlin.problem1846

import kotlin.math.min

/**
 * LeetCode page: [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N);
     */
    fun maximumElementAfterDecrementingAndRearranging(arr: IntArray): Int {
        val sorted = arr.clone().apply { sort() }
        sorted[0] = 1

        for (index in 1..<sorted.size) {
            sorted[index] = min(sorted[index], 1 + sorted[index - 1])
        }
        return sorted.last()
    }
}