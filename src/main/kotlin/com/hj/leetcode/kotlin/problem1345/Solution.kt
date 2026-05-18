package com.hj.leetcode.kotlin.problem1345

/**
 * LeetCode page: [1345. Jump Game IV](https://leetcode.com/problems/jump-game-iv/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the size of arr.
    fun minJumps(arr: IntArray): Int {
        val visited = BooleanArray(arr.size)
        val bfsQueue = ArrayDeque<Int>()
        val valueIndices = arr.groupIndicesByValue()

        var jumps = 0
        bfsQueue.add(0)
        visited[0] = true

        while (!visited[arr.lastIndex]) {
            jumps++
            repeat(bfsQueue.size) {
                val currIndex = bfsQueue.removeFirst()

                val left = currIndex - 1
                if (0 <= left && !visited[left]) {
                    bfsQueue.add(left)
                    visited[left] = true
                }

                val right = currIndex + 1
                if (right < arr.size && !visited[right]) {
                    bfsQueue.add(right)
                    visited[right] = true
                }

                val teleport = valueIndices.remove(arr[currIndex]) ?: emptyList()
                for (index in teleport) {
                    if (!visited[index]) {
                        bfsQueue.add(index)
                        visited[index] = true
                    }
                }
            }
        }

        return jumps
    }

    private fun IntArray.groupIndicesByValue(): MutableMap<Int, MutableList<Int>> {
        val map = mutableMapOf<Int, MutableList<Int>>()
        for ((index, value) in this.withIndex()) {
            map.computeIfAbsent(value) { mutableListOf() }.add(index)
        }
        return map
    }
}
