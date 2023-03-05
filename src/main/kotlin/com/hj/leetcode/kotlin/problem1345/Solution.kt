package com.hj.leetcode.kotlin.problem1345

/**
 * LeetCode page: [1345. Jump Game IV](https://leetcode.com/problems/jump-game-iv/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of arr;
     */
    fun minJumps(arr: IntArray): Int {
        var step = 0
        val indicesAtStep = ArrayDeque<Int>().apply { addLast(0) }
        val visited = BooleanArray(arr.size).apply { this[0] = true }
        val pendingIndices = arr.groupPendingIndicesByValue()

        while (indicesAtStep.isNotEmpty()) {
            repeat(indicesAtStep.size) {
                val currIndex = indicesAtStep.removeFirst()
                if (currIndex == arr.lastIndex) {
                    return step
                }

                val prevIndex = currIndex - 1
                if (prevIndex >= 0 && !visited[prevIndex]) {
                    indicesAtStep.addLast(prevIndex)
                    visited[prevIndex] = true
                }

                val nextIndex = currIndex + 1
                if (nextIndex < arr.size && !visited[nextIndex]) {
                    indicesAtStep.addLast(nextIndex)
                    visited[nextIndex] = true
                }

                val currValue = arr[currIndex]
                val indicesWithSameValue = pendingIndices[currValue] ?: mutableListOf()
                for (index in indicesWithSameValue) {
                    if (!visited[index]) {
                        indicesAtStep.addLast(index)
                        visited[index] = true
                    }
                }
                indicesWithSameValue.clear()
            }

            step++
        }
        throw IllegalStateException()
    }

    private fun IntArray.groupPendingIndicesByValue(): Map<Int, MutableList<Int>> {
        val result = hashMapOf<Int, MutableList<Int>>()
        for (index in 1 until size) {
            result
                .computeIfAbsent(this[index]) { mutableListOf() }
                .add(index)
        }
        return result
    }
}