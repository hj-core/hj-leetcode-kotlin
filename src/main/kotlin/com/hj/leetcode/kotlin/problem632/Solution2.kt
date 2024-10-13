package com.hj.leetcode.kotlin.problem632

import java.util.*

/**
 * LeetCode page: [632. Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/);
 */
class Solution2 {
    /* Complexity:
     * Time O(NLog(k)) and Space O(k) where k is the size of nums and
     * N is the flattened size of nums.
     */
    fun smallestRange(nums: List<List<Int>>): IntArray {
        // Exactly one Cell from each row, sorted by value
        val sortedCells =
            TreeSet<Cell>(
                compareBy(
                    { (row, col) -> nums[row][col] },
                    { cell -> cell.row }, // Necessary because of how TreeSet distinguish identity
                ),
            )
        for (row in nums.indices) {
            sortedCells.add(Cell(row, 0))
        }

        var result = rangeOfValues(sortedCells, nums)
        while (sortedCells.size == nums.size) {
            val range = rangeOfValues(sortedCells, nums)
            if (isSmaller(range, result)) {
                result = range
            }
            // Advance the left boundary of range
            val (row, col) = checkNotNull(sortedCells.pollFirst())
            if (col < nums[row].lastIndex) {
                sortedCells.add(Cell(row, col + 1))
            }
        }
        return result
    }

    private data class Cell(
        val row: Int,
        val col: Int,
    )

    private fun rangeOfValues(
        sortedCells: TreeSet<Cell>,
        nums: List<List<Int>>,
    ): IntArray = intArrayOf(nums.valueAt(sortedCells.first()), nums.valueAt(sortedCells.last()))

    private fun List<List<Int>>.valueAt(cell: Cell): Int = this[cell.row][cell.col]

    private fun isSmaller(
        range: IntArray,
        compareTo: IntArray,
    ): Boolean {
        val widthDiff = (range[1] - range[0]) - (compareTo[1] - compareTo[0])
        return when {
            widthDiff < 0 -> true
            widthDiff > 0 -> false
            else -> range[0] < compareTo[0]
        }
    }
}
