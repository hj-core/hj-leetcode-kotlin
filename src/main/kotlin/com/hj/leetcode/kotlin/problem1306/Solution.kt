package com.hj.leetcode.kotlin.problem1306

/**
 * LeetCode page: [1306. Jump Game III](https://leetcode.com/problems/jump-game-iii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of arr.
    fun canReach(
        arr: IntArray,
        start: Int,
    ): Boolean = dfs(start, arr, BooleanArray(arr.size))

    private fun dfs(
        start: Int,
        arr: IntArray,
        visited: BooleanArray,
    ): Boolean {
        if (start !in arr.indices || visited[start]) {
            return false
        }
        if (arr[start] == 0) {
            return true
        }

        visited[start] = true
        return dfs(start - arr[start], arr, visited) || dfs(start + arr[start], arr, visited)
    }
}
