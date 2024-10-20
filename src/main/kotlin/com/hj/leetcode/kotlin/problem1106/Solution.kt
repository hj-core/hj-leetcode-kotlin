package com.hj.leetcode.kotlin.problem1106

/**
 * LeetCode page: [1106. Parsing A Boolean Expression](https://leetcode.com/problems/parsing-a-boolean-expression/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of expression.
     */
    fun parseBoolExpr(expression: String): Boolean {
        val stack = mutableListOf<Char>()
        for (c in expression) {
            if (c != ')') {
                stack.add(c)
                continue
            }

            val operands = mutableListOf<Boolean>()
            while (stack.last() != '(') {
                when (stack.removeLast()) {
                    't' -> operands.add(true)
                    'f' -> operands.add(false)
                }
            }
            stack.removeLast()
            val eval =
                when (stack.removeLast()) {
                    '!' -> !operands[0].also { check(operands.size == 1) }
                    '&' -> operands.reduce(Boolean::and)
                    '|' -> operands.reduce(Boolean::or)
                    else -> throw IllegalArgumentException()
                }.let { if (it) 't' else 'f' }
            stack.add(eval)
        }
        check(stack.size == 1 && stack[0] in "tf")
        return stack[0] == 't'
    }
}
