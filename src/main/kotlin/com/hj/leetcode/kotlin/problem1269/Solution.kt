package com.hj.leetcode.kotlin.problem1269

/**
 * LeetCode page: [1269. Number of Ways to Stay in the Same Place After Some Steps](https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/);
 */
class Solution {

    private val modulo = 1_000_000_007

    /* Complexity:
     * Time O(steps * N) and Space O(steps * N) where N represents the minimum
     * between steps and arrLen;
     */
    fun numWays(steps: Int, arrLen: Int): Int {
        return numWaysToIndexZero(0, steps, arrLen, hashMapOf())
    }

    private fun numWaysToIndexZero(
        fromIndex: Int,
        steps: Int,
        arrLen: Int,
        memoization: MutableMap<Pair<Int, Int>, Int> // key=pair(fromIndex, steps)
    ): Int {
        if (fromIndex < 0 || fromIndex >= arrLen || steps < fromIndex) {
            return 0
        }
        if (steps == 0) {
            return 1
        }

        val state = Pair(fromIndex, steps)
        if (state in memoization) {
            return checkNotNull(memoization[state])
        }

        var result = 0
        result = (result + numWaysToIndexZero(fromIndex, steps - 1, arrLen, memoization)) % modulo
        result = (result + numWaysToIndexZero(fromIndex + 1, steps - 1, arrLen, memoization)) % modulo
        result = (result + numWaysToIndexZero(fromIndex - 1, steps - 1, arrLen, memoization)) % modulo

        memoization[state] = result
        return result
    }
}