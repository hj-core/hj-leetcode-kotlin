package com.hj.leetcode.kotlin.problem679

/**
 * LeetCode page: [679. 24 Game](https://leetcode.com/problems/24-game/);
 */
class Solution {
    // Complexity:
    // Time O(N!*(N-1)!*M^(N-1)*((N-1)!+NM) and Space O(N)
    // where N is the length of cards and M is the number
    // of possible operations.
    fun judgePoint24(cards: IntArray): Boolean {
        val newCards = Array(cards.size) { intArrayOf(cards[it], 1) }
        for (pos3 in 0..<4) {
            newCards[3] = newCards[pos3].also { newCards[pos3] = newCards[3] }
            for (pos2 in 0..<3) {
                newCards[2] = newCards[pos2].also { newCards[pos2] = newCards[2] }
                for (pos1 in 0..<2) {
                    newCards[1] = newCards[pos1].also { newCards[pos1] = newCards[1] }
                    for (op0 in 0..<4) {
                        for (op1 in 0..<4) {
                            for (op2 in 0..<4) {
                                for (order in 0..<5) {
                                    val (numerator, denominator) = evaluate(newCards, op0, op1, op2, order)
                                    if (denominator != 0 && numerator == denominator * 24) {
                                        return true
                                    }
                                }
                            }
                        }
                    }
                    newCards[1] = newCards[pos1].also { newCards[pos1] = newCards[1] }
                }
                newCards[2] = newCards[pos2].also { newCards[pos2] = newCards[2] }
            }
            newCards[3] = newCards[pos3].also { newCards[pos3] = newCards[3] }
        }
        return false
    }

    // Returns the resulting (numerator, denominator).
    private fun evaluate(
        cards: Array<IntArray>,
        op0: Int,
        op1: Int,
        op2: Int,
        order: Int,
    ): IntArray =
        when (order) {
            0 -> evaluateOp(op0, cards[0], evaluateOp(op1, cards[1], evaluateOp(op2, cards[2], cards[3])))
            1 -> evaluateOp(op2, evaluateOp(op1, evaluateOp(op0, cards[0], cards[1]), cards[2]), cards[3])
            2 -> evaluateOp(op1, evaluateOp(op0, cards[0], cards[1]), evaluateOp(op2, cards[2], cards[3]))
            3 -> evaluateOp(op0, cards[0], evaluateOp(op2, evaluateOp(op1, cards[1], cards[2]), cards[3]))
            4 -> evaluateOp(op2, evaluateOp(op0, cards[0], evaluateOp(op1, cards[1], cards[2])), cards[3])
            else -> throw NoWhenBranchMatchedException("invalid order $order")
        }

    // Returns the resulting (numerator, denominator).
    private fun evaluateOp(
        op: Int,
        a: IntArray,
        b: IntArray,
    ): IntArray =
        when (op) {
            0 -> intArrayOf(a[0] * b[1] + a[1] * b[0], a[1] * b[1])
            1 -> intArrayOf(a[0] * b[1] - a[1] * b[0], a[1] * b[1])
            2 -> intArrayOf(a[0] * b[0], a[1] * b[1])
            3 -> intArrayOf(a[0] * b[1], a[1] * b[0])
            else -> throw NoWhenBranchMatchedException("invalid op $op")
        }
}
