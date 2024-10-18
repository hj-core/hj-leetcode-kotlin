package com.hj.leetcode.kotlin.problem2044

/**
 * LeetCode page: [2044. Count Number of Maximum Bitwise-OR Subsets](https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/);
 */
class Solution {
    /* Complexity:
     * Time O(2^N) and Space O(N) where N is the size of nums.
     */
    fun countMaxOrSubsets(nums: IntArray): Int {
        val maxOr = nums.reduce { acc, i -> acc or i }
        return dfs(0, nums, maxOr, 0)
    }

    private fun dfs(
        index: Int,
        nums: IntArray,
        maxOr: Int,
        pathOr: Int,
    ): Int {
        if (pathOr == maxOr) {
            return 1 shl (nums.size - index)
        }
        if (index == nums.size) {
            return 0
        }
        return dfs(index + 1, nums, maxOr, pathOr) + dfs(index + 1, nums, maxOr, pathOr or nums[index])
    }
}
