package com.hj.leetcode.kotlin.problem752

/**
 * LeetCode page: [752. Open the Lock](https://leetcode.com/problems/open-the-lock/);
 */
class Solution {
    /* Complexity:
     * Time O(D+V) and Space O(D+V) where D is the size of deadends,
     * and V is the number of possible wheel numbers, which is 10000 in this problem;
     */
    fun openLock(deadends: Array<String>, target: String): Int {
        val deadEnds = deadends.toSet()
        if ("0000" in deadends) {
            return -1
        }

        var result = 0
        val bfsQueue = ArrayDeque<String>()
        val visited = mutableSetOf<String>()

        bfsQueue.addLast("0000")
        visited.add("0000")
        while (bfsQueue.isNotEmpty()) {
            repeat(bfsQueue.size) {
                val wheels = bfsQueue.removeFirst()
                if (wheels == target) {
                    return result
                }
                for (next in byOneTurn(wheels)) {
                    if (next in visited || next in deadEnds) {
                        continue
                    }
                    bfsQueue.addLast(next)
                    visited.add(next)
                }
            }
            result++
        }
        return -1
    }

    private fun byOneTurn(wheels: String): Set<String> {
        val result = mutableSetOf<String>()
        for ((i, char) in wheels.withIndex()) {
            val next = StringBuilder(wheels)
            if (char == '9') {
                next[i] = '0'
            } else {
                next[i] = char + 1
            }
            result.add(next.toString())

            if (char == '0') {
                next[i] = '9'
            } else {
                next[i] = char - 1
            }
            result.add(next.toString())
        }
        return result
    }
}