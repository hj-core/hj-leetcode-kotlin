package com.hj.leetcode.kotlin.problem241

/**
 * LeetCode page: [241. Different Ways to Add Parentheses](https://leetcode.com/problems/different-ways-to-add-parentheses/);
 *
 * TODO : Analyze the complexity of solution and may relate to the Catalan Numbers ([See Ref](https://people.math.sc.edu/howard/Classes/554b/catalan.pdf));
 */
class Solution {
    /* Complexity:
     * Time O(???) and Space O(???);
     */
    fun diffWaysToCompute(expression: String): List<Int> =
        diffWaysToCompute(
            expression = expression,
            subRange = expression.indices,
            memoization = mutableMapOf(),
        )

    private fun diffWaysToCompute(
        expression: String,
        subRange: IntRange,
        memoization: MutableMap<IntRange, List<Int>>,
    ): List<Int> {
        if (subRange in memoization) {
            return checkNotNull(memoization[subRange])
        }

        val result = mutableListOf<Int>()
        for (split in subRange) {
            if (!expression[split].isDigit()) {
                val leftList = diffWaysToCompute(expression, subRange.first..<split, memoization)
                val rightList = diffWaysToCompute(expression, (split + 1)..subRange.last, memoization)
                val operator = operator(expression[split])
                result.addAll(crossEvaluate(leftList, rightList, operator))
            }
        }
        if (result.isEmpty()) {
            result.add(expression.substring(subRange).toInt())
        }
        memoization[subRange] = result
        return result
    }

    private fun crossEvaluate(
        leftList: List<Int>,
        rightList: List<Int>,
        operator: (Int, Int) -> Int,
    ): Sequence<Int> =
        sequence {
            for (left in leftList) {
                for (right in rightList) {
                    yield(operator(left, right))
                }
            }
        }

    private fun operator(c: Char): (Int, Int) -> Int =
        when (c) {
            '+' -> { left, right -> left + right }
            '-' -> { left, right -> left - right }
            '*' -> { left, right -> left * right }
            else -> throw IllegalArgumentException()
        }
}
