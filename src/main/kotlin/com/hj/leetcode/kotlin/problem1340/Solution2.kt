package com.hj.leetcode.kotlin.problem1340

/**
 * LeetCode page: [1340. Jump Game V](https://leetcode.com/problems/jump-game-v/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of arr.
    fun maxJumps(
        arr: IntArray,
        d: Int,
    ): Int {
        val prevGreater = computePrevGreaterIndex(arr)
        val nextGreater = computeNextGreaterIndex(arr)

        // maxVisited[i]:= the maximum number of indices we can visit if we start
        // from index i.
        val maxVisited = IntArray(arr.size)
        var result = 1

        for (i in arr.indices) {
            result = maxOf(result, dfs(i, d, maxVisited, prevGreater, nextGreater))
        }

        return result
    }

    private fun computePrevGreaterIndex(arr: IntArray): IntArray {
        val prevGreater = IntArray(arr.size) { -2 * arr.size }
        for (i in 1 until arr.size) {
            prevGreater[i] = i - 1
            while (0 <= prevGreater[i] && arr[prevGreater[i]] <= arr[i]) {
                prevGreater[i] = prevGreater[prevGreater[i]]
            }
        }

        return prevGreater
    }

    private fun computeNextGreaterIndex(arr: IntArray): IntArray {
        val nextGreater = IntArray(arr.size) { 2 * arr.size }
        for (i in arr.lastIndex - 1 downTo 0) {
            nextGreater[i] = i + 1
            while (nextGreater[i] < arr.size && arr[nextGreater[i]] <= arr[i]) {
                nextGreater[i] = nextGreater[nextGreater[i]]
            }
        }

        return nextGreater
    }

    private fun dfs(
        start: Int,
        d: Int,
        maxVisited: IntArray,
        prevGreaterIndex: IntArray,
        nextGreaterIndex: IntArray,
    ): Int {
        if (maxVisited[start] > 0) {
            return maxVisited[start]
        }

        val left = prevGreaterIndex[start]
        if (start - left <= d) {
            maxVisited[start] =
                maxOf(
                    maxVisited[start],
                    dfs(left, d, maxVisited, prevGreaterIndex, nextGreaterIndex),
                )
        }

        val right = nextGreaterIndex[start]
        if (right - start <= d) {
            maxVisited[start] =
                maxOf(
                    maxVisited[start],
                    dfs(right, d, maxVisited, prevGreaterIndex, nextGreaterIndex),
                )
        }

        maxVisited[start] += 1
        return maxVisited[start]
    }
}
