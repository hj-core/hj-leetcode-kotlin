package com.hj.leetcode.kotlin.problem2305

/**
 * LeetCode page: [2305. Fair Distribution of Cookies](https://leetcode.com/problems/fair-distribution-of-cookies/);
 */
class Solution {
    /* Complexity:
     * Time O(k^N) and Space O(k+N) where N is the size of cookies;
     */
    fun distributeCookies(cookies: IntArray, k: Int): Int {
        var result = cookies.sum()
        dfs(cookies, 0, IntArray(k)) { distribution ->
            val unfairness = distribution.max()!!
            result = minOf(result, unfairness)
        }
        return result
    }

    private fun dfs(
        cookies: IntArray,
        nextCookieIndex: Int,
        distribution: IntArray,
        onDistributionComplete: (distribution: IntArray) -> Unit
    ) {
        if (nextCookieIndex >= cookies.size) {
            onDistributionComplete(distribution)
            return
        }

        for (index in distribution.indices) {
            distribution[index] += cookies[nextCookieIndex]
            dfs(cookies, nextCookieIndex + 1, distribution, onDistributionComplete)
            distribution[index] -= cookies[nextCookieIndex]
        }
    }
}