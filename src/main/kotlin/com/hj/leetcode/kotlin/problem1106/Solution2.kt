package com.hj.leetcode.kotlin.problem1106

/**
 * LeetCode page: [1106. Parsing A Boolean Expression](https://leetcode.com/problems/parsing-a-boolean-expression/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of expression.
     */
    fun parseBoolExpr(expression: String): Boolean = checkNotNull(parseBoolExpr(expression, intArrayOf(0), intArrayOf(0)))

    // Evaluate the boolean expression for current operator and modify states
    private fun parseBoolExpr(
        expression: String,
        index: IntArray,
        unbalancedLefts: IntArray, // Count of unbalanced lefts
    ): Boolean? =
        when (expression[index[0]]) {
            in "tf" -> {
                expression[index[0]] == 't'.also { index[0] += 1 }
            }

            '&' -> parseAnd(expression, index, unbalancedLefts)
            '|' -> parseOr(expression, index, unbalancedLefts)
            '!' -> parseNot(expression, index, unbalancedLefts)

            ',' -> {
                index[0] += 1
                null
            }

            ')' -> {
                index[0] += 1
                unbalancedLefts[0] -= 1
                null
            }

            else -> throw IllegalArgumentException()
        }

    private fun parseAnd(
        expression: String,
        index: IntArray,
        unbalancedLefts: IntArray,
    ): Boolean {
        require(expression[index[0]] == '&')
        val startingLefts = unbalancedLefts[0]
        index[0] += 2
        unbalancedLefts[0] += 1

        var result = true
        while (unbalancedLefts[0] > startingLefts) {
            val value = parseBoolExpr(expression, index, unbalancedLefts)
            if (value == false) {
                result = false
                break
            }
        }
        advanceIndex(expression, index, unbalancedLefts, startingLefts)
        return result
    }

    // Advance index to resume target unbalanced lefts
    private fun advanceIndex(
        expression: String,
        index: IntArray,
        unbalancedLefts: IntArray,
        targetLefts: Int,
    ) {
        while (unbalancedLefts[0] > targetLefts) {
            when (expression[index[0]]) {
                '(' -> unbalancedLefts[0] += 1
                ')' -> unbalancedLefts[0] -= 1
            }
            index[0] += 1
        }
    }

    private fun parseOr(
        expression: String,
        index: IntArray,
        unbalancedLefts: IntArray,
    ): Boolean {
        require(expression[index[0]] == '|')
        val startingLefts = unbalancedLefts[0]
        index[0] += 2
        unbalancedLefts[0] += 1

        var result = false
        while (unbalancedLefts[0] > startingLefts) {
            val value = parseBoolExpr(expression, index, unbalancedLefts)
            if (value == true) {
                result = true
                break
            }
        }
        advanceIndex(expression, index, unbalancedLefts, startingLefts)
        return result
    }

    private fun parseNot(
        expression: String,
        index: IntArray,
        unbalancedLefts: IntArray,
    ): Boolean {
        require(expression[index[0]] == '!')
        index[0] += 2
        unbalancedLefts[0] += 1
        return !checkNotNull(parseBoolExpr(expression, index, unbalancedLefts))
    }
}
