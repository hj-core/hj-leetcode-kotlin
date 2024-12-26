package com.hj.leetcode.kotlin.problem494

/**
 * LeetCode page: [494. Target Sum](https://leetcode.com/problems/target-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(NK) and Space O(NK)
     * where N and K are the size and sum of nums, respectively.
     */
    fun findTargetSumWays(
        nums: IntArray,
        target: Int,
    ): Int = dfs(0, nums, target, mutableMapOf())

    private fun dfs(
        start: Int,
        nums: IntArray,
        target: Int,
        memoization: MutableMap<Int, MutableMap<Int, Int>>, // memoization[start][target]
    ): Int {
        if (start == nums.size) {
            return if (target == 0) 1 else 0
        }
        if (memoization[start]?.containsKey(target) == true) {
            return memoization[start]!![target]!!
        }
        val withPlus = dfs(start + 1, nums, target - nums[start], memoization)
        val withMinus = dfs(start + 1, nums, target + nums[start], memoization)
        val result = withPlus + withMinus
        memoization.computeIfAbsent(start) { mutableMapOf() }.computeIfAbsent(target) { result }
        return result
    }
}
