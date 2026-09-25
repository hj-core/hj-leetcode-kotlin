package com.hj.leetcode.kotlin.problem1096

/**
 * LeetCode page: [1096. Brace Expansion II](https://leetcode.com/problems/brace-expansion-ii/);
 */
class Solution {
    // Complexity:
    // Time O(???) and Space O(???).
    fun braceExpansionII(expression: String): List<String> {
        val expr = expression.toCharArray()
        val operands = mutableListOf<Operand>()
        val ops = mutableListOf<Operator>()
        var i = 0
        while (i < expr.size) {
            if (hasImplicitCross(expr, i)) {
                ops.add(Operator.Cross)
            }

            when (expr[i]) {
                '{' -> {
                    ops.add(Operator.Null)
                    i++
                }

                '}' -> {
                    closeLastOpen(operands, ops)
                    i++
                }

                ',' -> {
                    ops.add(Operator.Union)
                    i++
                }

                in 'a'..'z' -> {
                    var j = i + 1
                    while (j < expr.size && expr[j] in 'a'..'z') {
                        j++
                    }
                    operands.add(Operand.new(expr.concatToString(i, j)))
                    i = j
                }
            }

            if (ops.isNotEmpty() && ops.last() == Operator.Cross) {
                processLast(operands, ops)
            }
        }

        // No remaining braces
        var right = operands.removeLast()
        while (ops.isNotEmpty()) {
            val left = operands.removeLast()
            val op = ops.removeLast()
            right = eval(left, right, op)
        }

        return right.sorted()
    }

    private class Operand : HashSet<String>() {
        fun union(other: Operand): Operand {
            val result = Operand()
            result.addAll(this)
            result.addAll(other)
            return result
        }

        fun cross(other: Operand): Operand {
            val result = Operand()
            for (s1 in this) {
                for (s2 in other) {
                    result.add(s1 + s2)
                }
            }
            return result
        }

        companion object {
            fun new(vararg strs: String): Operand = Operand().apply { addAll(strs) }
        }
    }

    private enum class Operator {
        Union,
        Cross,
        Null,
    }

    private fun hasImplicitCross(
        expr: CharArray,
        idx: Int,
    ): Boolean {
        if (idx == 0 || expr[idx] == '}' || expr[idx] == ',') {
            return false
        }
        return expr[idx - 1] == '}' || expr[idx - 1] in 'a'..'z'
    }

    private fun closeLastOpen(
        operands: MutableList<Operand>,
        ops: MutableList<Operator>,
    ) {
        var right = operands.removeLast()
        while (ops.last() != Operator.Null) {
            val left = operands.removeLast()
            val op = ops.removeLast()
            right = eval(left, right, op)
        }
        ops.removeLast()
        operands.add(right)
    }

    private fun eval(
        left: Operand,
        right: Operand,
        op: Operator,
    ): Operand =
        when (op) {
            Operator.Union -> left.union(right)
            Operator.Cross -> left.cross(right)
            Operator.Null -> throw IllegalArgumentException()
        }

    private fun processLast(
        operands: MutableList<Operand>,
        ops: MutableList<Operator>,
    ) {
        val right = operands.removeLast()
        val left = operands.removeLast()
        val op = ops.removeLast()
        operands.add(eval(left, right, op))
    }
}
