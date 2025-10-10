package com.hj.leetcode.kotlin.problem3484

/**
 * LeetCode page: [3484. Design Spreadsheet](https://leetcode.com/problems/design-spreadsheet/);
 */
class Solution

class Spreadsheet(
    rows: Int,
) {
    private val values = hashMapOf<String, Int>()

    // Complexity for N calls:
    // Time O(N) and Space O(N).
    fun setCell(
        cell: String,
        value: Int,
    ) {
        values[cell] = value
    }

    // Complexity:
    // Time O(1) and Space O(1).
    fun resetCell(cell: String) {
        values.remove(cell)
    }

    // Complexity:
    // Time O(L) and Space O(L) where L is the length of formula.
    fun getValue(formula: String): Int =
        formula
            .substring(1)
            .split('+')
            .fold(0) { acc, elem ->
                acc + (if (elem[0] < 'A') elem.toInt() else values[elem] ?: 0)
            }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * var obj = Spreadsheet(rows)
 * obj.setCell(cell,value)
 * obj.resetCell(cell)
 * var param_3 = obj.getValue(formula)
 */
