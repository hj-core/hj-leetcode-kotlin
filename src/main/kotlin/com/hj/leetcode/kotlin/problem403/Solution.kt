package com.hj.leetcode.kotlin.problem403

/**
 * LeetCode page: [403. Frog Jump](https://leetcode.com/problems/frog-jump/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of stones;
     */
    fun canCross(stones: IntArray): Boolean {
        return canCross(0, 0, stones.toSet(), stones.last(), hashMapOf())
    }

    private fun canCross(
        position: Int,
        lastJumpUnits: Int,
        stones: Set<Int>,
        goal: Int,
        memoization: MutableMap<Pair<Int, Int>, Boolean> // key=Pair(position, lastJumpUnits)
    ): Boolean {
        if (position == goal) {
            return true
        }

        if (position !in stones) {
            return false
        }

        val state = Pair(position, lastJumpUnits)
        if (state in memoization) {
            return checkNotNull(memoization[state])
        }

        val possibleJumps = listOf(lastJumpUnits - 1, lastJumpUnits, lastJumpUnits + 1)
        val result = possibleJumps.any {
            it > 0 && canCross(position + it, it, stones, goal, memoization)
        }
        memoization[state] = result
        return result
    }
}