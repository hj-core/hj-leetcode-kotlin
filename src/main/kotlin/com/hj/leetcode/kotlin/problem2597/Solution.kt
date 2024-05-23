package com.hj.leetcode.kotlin.problem2597

/**
 * LeetCode page: [2597. The Number of Beautiful Subsets](https://leetcode.com/problems/the-number-of-beautiful-subsets/);
 */
class Solution {
    /* Complexity:
     * Time O(2^N) and Space O(N) where N is the size of nums;
     */
    fun beautifulSubsets(nums: IntArray, k: Int): Int {
        return countBeautifulSubsets(nums, k, 0, hashMapOf())
    }

    private fun countBeautifulSubsets(
        nums: IntArray,
        k: Int,
        index: Int,
        subset: MutableMap<Int, Int>,
    ): Int {
        if (index == nums.size) {
            return if (subset.isNotEmpty()) 1 else 0
        }
        var result = 0
        if ((nums[index] + k) !in subset && (nums[index] - k) !in subset) {
            subset.compute(nums[index]) { _, v -> 1 + (v ?: 0) }
            result += countBeautifulSubsets(nums, k, index + 1, subset)
            subset.compute(nums[index]) { _, v -> if (v == 1 || v == null) null else v - 1}
        }
        result += countBeautifulSubsets(nums, k, index + 1, subset)
        return result
    }
}