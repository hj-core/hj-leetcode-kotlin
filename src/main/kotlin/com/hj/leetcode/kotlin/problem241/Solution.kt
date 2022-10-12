package com.hj.leetcode.kotlin.problem241

/**
 * LeetCode page: [241. Different Ways to Add Parentheses](https://leetcode.com/problems/different-ways-to-add-parentheses/);
 *
 * TODO : Analyze the complexity of solution and may relate to the Catalan Numbers ([See Ref](https://people.math.sc.edu/howard/Classes/554b/catalan.pdf));
 */
class Solution {

    private val operator = charArrayOf('+', '-', '*')

    /* Complexity:
     * Time O(???) and Space O(???);
     */
    fun diffWaysToCompute(expression: String): List<Int> {
        val memorizedIndexRangeResult = hashMapOf<IntRange, List<Int>>()

        return diffWaysToComputeSubExpression(
            indexRange = expression.indices,
            fullExpression = expression,
            memorizedResults = memorizedIndexRangeResult
        )
    }

    private fun diffWaysToComputeSubExpression(
        indexRange: IntRange,
        fullExpression: String,
        memorizedResults: MutableMap<IntRange, List<Int>>
    ): List<Int> {
        return memorizedResults.getOrPut(indexRange) {
            val result = mutableListOf<Int>()
            var noOperator = true

            for (index in indexRange) {
                val char = fullExpression[index]
                if (char !in operator) continue

                noOperator = false

                val leftRange = indexRange.first until index
                val leftResult = diffWaysToComputeSubExpression(leftRange, fullExpression, memorizedResults)

                val rightRange = index + 1..indexRange.last
                val rightResult = diffWaysToComputeSubExpression(rightRange, fullExpression, memorizedResults)

                val operation = getOperation(char)
                val currResult = operate(leftResult, rightResult, operation)
                result.addAll(currResult)
            }

            if (noOperator) result.add(fullExpression.slice(indexRange).toInt())
            result
        }
    }

    private fun getOperation(operatorChar: Char): (int1: Int, int2: Int) -> Int {
        return when (operatorChar) {
            '+' -> { int1: Int, int2: Int -> int1 + int2 }
            '-' -> { int1: Int, int2: Int -> int1 - int2 }
            '*' -> { int1: Int, int2: Int -> int1 * int2 }
            else -> throw IllegalArgumentException()
        }
    }

    private fun operate(left: List<Int>, right: List<Int>, operation: (int1: Int, int2: Int) -> Int): List<Int> {
        val result = mutableListOf<Int>()

        for (int1 in left) {
            for (int2 in right) {
                result.add(operation(int1, int2))
            }
        }
        return result
    }
}