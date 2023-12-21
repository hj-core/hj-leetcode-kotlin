package com.hj.leetcode.kotlin.problem224

/**
 * LeetCode page: [224. Basic Calculator](https://leetcode.com/problems/basic-calculator/);
 */
class Solution {
    /* Complexity:
     * Time O(|s|) and Space O(|s|);
     */
    fun calculate(s: String): Int {
        return Evaluator.evaluate(s)
    }

    private class Evaluator private constructor(private val expression: String) {
        private val operator = charArrayOf('+', '-')
        private var result = 0
        private val currValue = StringBuilder()
        private val parenthesesSigns = mutableListOf<Int>()
        private var combinedSign = 1

        private fun evaluate(): Int {
            for (char in expression) {
                when {
                    char.isWhitespace() -> handleWhiteSpace()
                    char.isDigit() -> handleDigit(char)
                    char.isOperator() -> handleOperator(char)
                    char == '(' -> handleLeftParentheses()
                    char == ')' -> handleRightParentheses()
                    else -> throw IllegalArgumentException()
                }
            }
            addCurrentValue()
            currValue.clear()
            return result
        }

        private fun handleWhiteSpace() {}

        private fun handleDigit(char: Char) {
            currValue.append(char)
        }

        private fun Char.isOperator() = this in operator

        private fun handleOperator(char: Char) {
            addCurrentValue()
            currValue.clear()
            currValue.append(char)
        }

        private fun addCurrentValue() {
            if (currValue.isNotEmpty()) {
                val value = currValue.toString().toInt()
                result += value * combinedSign
            }
        }

        private fun handleLeftParentheses() {
            if (currValue.isEmpty()) {
                parenthesesSigns.add(1)
                return
            }

            val currSign = when (currValue.last()) {
                '+' -> 1
                '-' -> -1
                else -> throw IllegalArgumentException()
            }

            parenthesesSigns.add(currSign)
            combinedSign *= currSign
            currValue.clear()
        }

        private fun handleRightParentheses() {
            addCurrentValue()
            currValue.clear()
            combinedSign *= parenthesesSigns.last()
            parenthesesSigns.apply { removeAt(lastIndex) }
        }

        companion object {
            fun evaluate(expression: String): Int = Evaluator(expression).evaluate()
        }
    }
}