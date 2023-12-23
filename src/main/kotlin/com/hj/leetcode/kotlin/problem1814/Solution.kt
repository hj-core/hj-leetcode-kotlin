package com.hj.leetcode.kotlin.problem1814

/**
 * LeetCode page: [1814. Count Nice Pairs in an Array](https://leetcode.com/problems/count-nice-pairs-in-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun countNicePairs(nums: IntArray): Int {
        val modulo = 1_000_000_007
        val diffFreq = hashMapOf<Int, Int>()
        var result = 0
        for (index in nums.indices.reversed()) {
            val diff = nums[index] - rev(nums[index])
            val freq = diffFreq[diff] ?: 0
            result = (result + freq) % modulo
            diffFreq[diff] = freq + 1
        }
        return result
    }

    private fun rev(x: Int): Int {
        var result = 0
        var num = x
        while (num != 0) {
            result = result * 10 + num % 10
            num /= 10
        }
        return result
    }
}