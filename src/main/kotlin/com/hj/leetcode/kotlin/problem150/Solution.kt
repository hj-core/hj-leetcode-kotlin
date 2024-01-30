package com.hj.leetcode.kotlin.problem150

/**
 * LeetCode page: [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of tokens;
     */
    fun evalRPN(tokens: Array<String>): Int {
        val operator = listOf("+", "-", "*", "/")
        val operandStack = ArrayDeque<Int>()
        for (token in tokens) {
            when (token) {
                in operator -> {
                    val operand2 = operandStack.removeLast()
                    val operand1 = operandStack.removeLast()
                    val subResult = operate(operand1, operand2, token[0])
                    operandStack.addLast(subResult)
                }

                else -> operandStack.addLast(token.toInt())
            }
        }
        return operandStack.last()
    }

    private fun operate(operand1: Int, operand2: Int, operator: Char): Int {
        return when (operator) {
            '+' -> operand1 + operand2
            '-' -> operand1 - operand2
            '*' -> operand1 * operand2
            '/' -> operand1 / operand2
            else -> throw IllegalArgumentException()
        }
    }
}