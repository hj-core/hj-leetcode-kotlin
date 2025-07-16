package com.hj.leetcode.kotlin.problem3201

/**
 * LeetCode page: [3201. Find the Maximum Length of Valid Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maximumLength(nums: IntArray): Int {
        // The possible patterns include all odds, all evens,
        // alternating odd-even, and alternating even-odd. For the
        // alternating patterns, we only consider the one that starts
        // with the parity of nums[0], since it is longer than the
        // other by one.
        var cntEvens = 0
        var alterLen = 0
        var alterBit = nums[0] and 1
        for (num in nums) {
            if (num and 1 == 0) {
                cntEvens++
            }
            if (num and 1 == alterBit) {
                alterLen++
                alterBit = alterBit xor 1
            }
        }
        return maxOf(cntEvens, nums.size - cntEvens, alterLen)
    }
}
