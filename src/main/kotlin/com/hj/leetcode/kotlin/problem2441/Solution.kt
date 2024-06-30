package com.hj.leetcode.kotlin.problem2441

import kotlin.math.abs
import kotlin.math.max

/**
 * LeetCode page: [2441. Largest Positive Integer That Exists With Its Negative](https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of nums;
     */
    fun findMaxK(nums: IntArray): Int {
        var result = -1
        val visited = hashSetOf<Int>()
        for (num in nums) {
            if (-num in visited) {
                result = max(result, abs(num))
            }
            visited.add(num)
        }
        return result
    }
}