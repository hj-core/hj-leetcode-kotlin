package com.hj.leetcode.kotlin.problem268

/**
 * LeetCode page: [268. Missing Number](https://leetcode.com/problems/missing-number/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun missingNumber(nums: IntArray): Int {
        return nums.reduce(Int::xor) xor (1..nums.size).reduce(Int::xor)
    }
}