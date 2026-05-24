package com.hj.leetcode.kotlin.problem1340

/**
 * LeetCode page: [1340. Jump Game V](https://leetcode.com/problems/jump-game-v/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of arr.
    fun maxJumps(
        arr: IntArray,
        d: Int,
    ): Int {
        val n = arr.size
        val indexValues = IntArray(n) { pack(it, arr[it]) }
        indexValues.sort() // sort by value and break ties by index

        val leftNearest = computeLeftNearestLargerIndex(arr)
        val rightNearest = computeRightNearestLargerIndex(arr)

        // maxVisited[i]:= the maximum number of indices we can visit if we start
        // from index i.
        val maxVisited = IntArray(n)
        var result = 1

        for (indexValue in indexValues) {
            val i = getIndex(indexValue)
            maxVisited[i]++
            result = maxOf(result, maxVisited[i])

            val left = leftNearest[i]
            if (i - left <= d) {
                maxVisited[left] = maxOf(maxVisited[left], maxVisited[i])
            }

            val right = rightNearest[i]
            if (right - i <= d) {
                maxVisited[right] = maxOf(maxVisited[right], maxVisited[i])
            }
        }

        return result
    }

    private fun pack(
        index: Int,
        value: Int,
    ): Int {
        require(index < 1000) {
            "Violated problem constraint 1 <= arr.length <= 1000: index = $index"
        }
        require(value in 1..<100_001) {
            "Violated problem constraint 1 <= arr[i] <= 10^5: value = $value"
        }
        return (value shl 12) or index
    }

    private fun getIndex(indexValue: Int): Int = indexValue and 0xFFF

    private fun computeLeftNearestLargerIndex(arr: IntArray): IntArray {
        val nearestLargerIndex = IntArray(arr.size) { -2 * arr.size }
        for (i in 1 until arr.size) {
            var j = i - 1
            while (0 <= j && arr[j] <= arr[i]) {
                j = nearestLargerIndex[j]
            }
            nearestLargerIndex[i] = j
        }

        return nearestLargerIndex
    }

    private fun computeRightNearestLargerIndex(arr: IntArray): IntArray {
        val nearestLargerIndex = IntArray(arr.size) { 2 * arr.size }
        for (i in arr.lastIndex - 1 downTo 0) {
            var j = i + 1
            while (j < arr.size && arr[j] <= arr[i]) {
                j = nearestLargerIndex[j]
            }
            nearestLargerIndex[i] = j
        }

        return nearestLargerIndex
    }
}
