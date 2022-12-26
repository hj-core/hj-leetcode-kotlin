package com.hj.leetcode.kotlin.problem2389

/**
 * LeetCode page: [2389. Longest Subsequence With Limited Sum](https://leetcode.com/problems/longest-subsequence-with-limited-sum/);
 */
class Solution {
    /* Complexity:
     * Time O((N+M)LogN) and Space O(N) where N and M are the size of nums and queries;
     */
    fun answerQueries(nums: IntArray, queries: IntArray): IntArray {
        val prefixSum = sortAndCalculatePrefixSum(nums)
        val ans = IntArray(queries.size) { index ->
            prefixSum
                .binarySearch(queries[index])
                .let { if (it < 0) -(it + 1) else it + 1 }
        }
        return ans
    }

    private fun sortAndCalculatePrefixSum(nums: IntArray): IntArray {
        val ans = nums.clone().apply { sort() }
        for (index in 1 until ans.size) {
            ans[index] += ans[index - 1]
        }
        return ans
    }
}