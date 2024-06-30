package com.hj.leetcode.kotlin.problem2149

/**
 * LeetCode page: [2149. Rearrange Array Elements by Sign](https://leetcode.com/problems/rearrange-array-elements-by-sign/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of nums;
     */
    fun rearrangeArray(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var positiveIndex = 0
        var negativeIndex = 1
        for (num in nums) {
            if (num < 0) {
                result[negativeIndex] = num
                negativeIndex += 2
            } else {
                result[positiveIndex] = num
                positiveIndex += 2
            }
        }
        return result
    }
}