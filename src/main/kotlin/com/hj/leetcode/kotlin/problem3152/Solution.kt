package com.hj.leetcode.kotlin.problem3152

/**
 * LeetCode page: [3152. Special Array II](https://leetcode.com/problems/special-array-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N)
     * where N is the length of nums and M is the length of queries.
     */
    fun isArraySpecial(
        nums: IntArray,
        queries: Array<IntArray>,
    ): BooleanArray {
        // dp[i]::= starting index of the longest special subarray ending at index i
        val dp = IntArray(nums.size)
        for (i in 1..<nums.size) {
            dp[i] = if (sameParity(nums[i], nums[i - 1])) i else dp[i - 1]
        }

        return BooleanArray(queries.size) {
            val (from, to) = queries[it]
            dp[to] <= from
        }
    }

    private fun sameParity(
        a: Int,
        b: Int,
    ): Boolean = (a and 1) == (b and 1)
}
