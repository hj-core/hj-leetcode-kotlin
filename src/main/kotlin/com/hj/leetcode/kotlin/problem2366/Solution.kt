package com.hj.leetcode.kotlin.problem2366

/**
 * LeetCode page: [2366. Minimum Replacements to Sort the Array](https://leetcode.com/problems/minimum-replacements-to-sort-the-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun minimumReplacement(nums: IntArray): Long {
        var result = 0L
        var upperBound = nums.last()

        for (index in nums.lastIndex - 1 downTo 0) {
            if (nums[index] <= upperBound) {
                upperBound = nums[index]
                continue
            }

            val split = (nums[index] / upperBound).let {
                if (nums[index] % upperBound == 0) it else it + 1
            }
            result += split - 1
            upperBound = nums[index] / split
        }
        return result
    }
}