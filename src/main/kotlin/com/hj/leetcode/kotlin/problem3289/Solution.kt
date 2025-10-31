package com.hj.leetcode.kotlin.problem3289

/**
 * LeetCode page: [3289. The Two Sneaky Numbers of Digitville](https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of nums.
    fun getSneakyNumbers(nums: IntArray): IntArray =
        nums
            .asList()
            .groupingBy { it }
            .eachCount()
            .filter { (_, freq) -> freq > 1 }
            .map(Map.Entry<Int, Int>::key)
            .toIntArray()
}
