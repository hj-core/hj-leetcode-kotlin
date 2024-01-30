package com.hj.leetcode.kotlin.problem150

/**
 * LeetCode page: [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/description/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of tokens;
     */
    fun evalRPN(tokens: Array<String>): Int {
        val operations = hashMapOf(
            "+" to { a: Int, b: Int -> a + b },
            "-" to { a: Int, b: Int -> a - b },
            "*" to { a: Int, b: Int -> a * b },
            "/" to { a: Int, b: Int -> a / b },
        )

        val operandStack = mutableListOf<Int>()
        for (token in tokens) {
            val parsed = operations[token]?.let { operation ->
                val operand2 = operandStack.removeLast()
                val operand1 = operandStack.removeLast()
                operation(operand1, operand2)
            } ?: token.toInt()
            operandStack.add(parsed)
        }
        check(operandStack.size == 1)
        return operandStack.last()
    }
}