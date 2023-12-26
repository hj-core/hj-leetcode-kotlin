package com.hj.leetcode.kotlin.problem1155

import kotlin.math.min

/**
 * LeetCode page: [1155. Number of Dice Rolls With Target Sum](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/);
 *
 * TODO : There is solution with space complexity O(1) ([see REF](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/discuss/1159705));
 */
class Solution {

    private val modulo = 1_000_000_007

    /* Complexity:
     * Time O(n * target) and Space O(n * target);
     */
    fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
        val symmetricalTarget = n * k - (target - n)
        return numRollsDp(n, k, min(target, symmetricalTarget), hashMapOf())
    }

    private fun numRollsDp(
        dices: Int,
        k: Int,
        targetSum: Int,
        memoization: MutableMap<DpState, Int>,
    ): Int {
        if (targetSum !in dices..dices * k) {
            return 0
        }
        if (dices == 1) {
            return if (targetSum in 1..k) 1 else 0
        }
        if (dices == targetSum) {
            return 1
        }

        val state = DpState(dices, targetSum)
        if (state in memoization) {
            return checkNotNull(memoization[state])
        }

        var result = (numRollsDp(dices, k, targetSum - 1, memoization)
                - numRollsDp(dices - 1, k, targetSum - 1 - k, memoization)
                + numRollsDp(dices - 1, k, targetSum - 1, memoization)
                ) % modulo
        if (result < 0) {
            result += modulo
        }
        memoization[state] = result
        return result
    }

    private data class DpState(val dices: Int, val targetSum: Int)
}