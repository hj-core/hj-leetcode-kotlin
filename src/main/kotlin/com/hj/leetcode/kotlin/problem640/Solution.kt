package com.hj.leetcode.kotlin.problem640

/**
 * LeetCode page: [640. Solve the Equation](https://leetcode.com/problems/solve-the-equation/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of equation;
     */
    fun solveEquation(equation: String): String {
        val solver = SingleVariableLinearEquationSolver()
        return solver.parseAndSolve(equation)
    }

    private class SingleVariableLinearEquationSolver {

        private var coefficient: Int = 0
        private var constant: Int = 0
        private val auxBuilder = StringBuilder()

        fun parseAndSolve(equation: String): String {
            parse(equation)
            return solve()
        }

        private fun parse(equation: String) {
            for (char in equation) {
                when (char) {
                    '+', '-' -> {
                        concludeAndClearAuxBuilder()
                        auxBuilder.append(char)
                    }
                    '=' -> {
                        concludeAndClearAuxBuilder()
                        reverseSign()
                    }
                    else -> auxBuilder.append(char)
                }
            }
            concludeAndClearAuxBuilder()
        }

        private fun concludeAndClearAuxBuilder() {
            if (auxBuilder.isEmpty()) return

            if (auxBuilder.last() == 'x') {
                auxBuilder.deleteCharAt(auxBuilder.lastIndex)
                when (auxBuilder.length) {
                    0 -> auxBuilder.append(1)
                    1 -> if (auxBuilder[0] == '+' || auxBuilder[0] == '-') auxBuilder.append(1)
                }
                coefficient -= auxBuilder.toString().toInt()
            } else {
                constant += auxBuilder.toString().toInt()
            }
            auxBuilder.clear()
        }

        private fun reverseSign() {
            coefficient = -coefficient
            constant = -constant
        }

        private fun solve(): String {
            return when {
                coefficient != 0 -> "x=${constant / coefficient}"
                constant == 0 -> "Infinite solutions"
                else -> "No solution"
            }
        }
    }
}