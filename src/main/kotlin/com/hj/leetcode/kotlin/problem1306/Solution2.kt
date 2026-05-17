package com.hj.leetcode.kotlin.problem1306

/**
 * LeetCode page: [1306. Jump Game III](https://leetcode.com/problems/jump-game-iii/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of arr.
    fun canReach(
        arr: IntArray,
        start: Int,
    ): Boolean {
        val visited = BooleanArray(arr.size)
        val stack = IntArray(arr.size)
        var top = 0
        stack[top] = start
        while (top >= 0) {
            val node = stack[top--]
            for (next in intArrayOf(node - arr[node], node + arr[node])) {
                if (next in arr.indices && !visited[next]) {
                    if (arr[next] == 0) {
                        return true
                    }
                    visited[next] = true
                    stack[++top] = next
                }
            }
        }

        return false
    }
}
