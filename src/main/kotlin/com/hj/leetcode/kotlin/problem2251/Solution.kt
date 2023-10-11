package com.hj.leetcode.kotlin.problem2251

/**
 * LeetCode page: [2251. Number of Flowers in Full Bloom](https://leetcode.com/problems/number-of-flowers-in-full-bloom/);
 */
class Solution {
    /* Complexity:
     * Time O((N+M)LogN) and Space O(N) where N is the size of flowers and
     * M is the size of people;
     */
    fun fullBloomFlowers(flowers: Array<IntArray>, people: IntArray): IntArray {
        val sortedStarts =
            IntArray(flowers.size) { flowers[it][0] }.apply { sort() }

        val sortedEnds =
            IntArray(flowers.size) { flowers[it][1] }.apply { sort() }

        return IntArray(people.size) { index ->
            numFlowersInFullBloom(people[index], sortedStarts, sortedEnds)
        }
    }

    private fun numFlowersInFullBloom(
        time: Int,
        sortedStarts: IntArray,
        sortedEnds: IntArray
    ): Int {
        val numStarted = leftInsertionIndex(time + 1, sortedStarts)
        val numEnded = leftInsertionIndex(time, sortedEnds)
        return numStarted - numEnded
    }

    /**
     * Return the leftmost index to insert the [target] while keeping
     * the [sorted] sorted.
     */
    private fun leftInsertionIndex(target: Int, sorted: IntArray): Int {
        if (sorted.isEmpty() || target <= sorted[0]) {
            return 0
        }
        if (sorted.last() < target) {
            return sorted.size
        }

        var left = 0
        var right = sorted.lastIndex

        while (left < right) {
            val mid = (left + right) ushr 1

            if (sorted[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}