package com.hj.leetcode.kotlin.problem1269

/**
 * LeetCode page: [1269. Number of Ways to Stay in the Same Place After Some Steps](https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/);
 */
class Solution {

    private val modulo = 1_000_000_007

    /* Complexity:
     * Time O(steps^2) and Space O(steps^2);
     */
    fun numWays(steps: Int, arrLen: Int): Int {
        return numWays(0, steps, arrLen, hashMapOf())
    }

    private fun numWays(
        index: Int,
        steps: Int,
        arrLen: Int,
        memoization: MutableMap<Pair<Int, Int>, Int>
    ): Int {
        if (index < 0 || index >= arrLen || steps < index) {
            return 0
        }
        if (steps == 0) {
            return 1
        }

        val state = Pair(index, steps)
        if (state in memoization) {
            return checkNotNull(memoization[state])
        }

        var result = 0
        result = (result + numWays(index, steps - 1, arrLen, memoization)) % modulo
        result = (result + numWays(index + 1, steps - 1, arrLen, memoization)) % modulo
        result = (result + numWays(index - 1, steps - 1, arrLen, memoization)) % modulo

        memoization[state] = result
        return result
    }
}