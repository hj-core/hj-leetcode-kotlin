package com.hj.leetcode.kotlin.problem2044

/**
 * LeetCode page: [2044. Count Number of Maximum Bitwise-OR Subsets](https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/);
 */
class Solution {
    /* Complexity:
     * Time O(2^N) and Space O(N) where N is the size of nums.
     */
    fun countMaxOrSubsets(nums: IntArray): Int {
        val maxOr = nums.reduce(Int::or)

        fun dfs(
            index: Int,
            pathOr: Int,
        ): Int =
            when {
                pathOr == maxOr -> 1 shl (nums.size - index)
                index == nums.size -> 0
                else -> dfs(index + 1, pathOr) + dfs(index + 1, pathOr or nums[index])
            }
        return dfs(0, 0)
    }
}
