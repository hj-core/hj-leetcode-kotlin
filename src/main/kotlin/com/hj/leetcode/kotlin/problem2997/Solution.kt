package com.hj.leetcode.kotlin.problem2997

/**
 * LeetCode page: [2997. Minimum Number of Operations to Make Array XOR Equal to K](https://leetcode.com/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun minOperations(nums: IntArray, k: Int): Int {
        val difference = k xor (nums.reduce { acc, i -> acc xor i })
        return difference.countOneBits()
    }
}