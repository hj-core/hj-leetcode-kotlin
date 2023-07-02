package com.hj.leetcode.kotlin.problem1601

/**
 * LeetCode page: [1601. Maximum Number of Achievable Transfer Requests](https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/);
 */
class Solution {
    /* Complexity:
     * Time O(n * 2^M) and Space O(n+M) where M is the size of requests;
     */
    fun maximumRequests(n: Int, requests: Array<IntArray>): Int {
        var result = 0
        dfs(requests, 0, 0, IntArray(n)) { numAcceptedRequests ->
            result = maxOf(result, numAcceptedRequests)
        }
        return result
    }

    private fun dfs(
        requests: Array<IntArray>,
        nextRequestIndex: Int,
        numAcceptedRequests: Int,
        netEmployeeChange: IntArray, // net change of each building
        onAchievableTransfer: (numAcceptedRequests: Int) -> Unit
    ) {
        if (nextRequestIndex == requests.size) {
            if (isAchievableTransfer(netEmployeeChange)) {
                onAchievableTransfer(numAcceptedRequests)
            }
            return
        }

        // Case that the request is not accepted
        dfs(requests, nextRequestIndex + 1, numAcceptedRequests, netEmployeeChange, onAchievableTransfer)

        // Case that the request is accepted. Apply backtracking on the netEmployeeChange.
        val (from, to) = requests[nextRequestIndex]
        netEmployeeChange[from]--
        netEmployeeChange[to]++

        dfs(requests, nextRequestIndex + 1, numAcceptedRequests + 1, netEmployeeChange, onAchievableTransfer)
        netEmployeeChange[to]--
        netEmployeeChange[from]++
    }

    private fun isAchievableTransfer(netEmployeeChange: IntArray): Boolean {
        return netEmployeeChange.all { it == 0 }
    }
}