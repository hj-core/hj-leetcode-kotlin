package com.hj.leetcode.kotlin.problem1861

/**
 * LeetCode page: [1861. Rotating the Box](https://leetcode.com/problems/rotating-the-box/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Auxiliary Space O(1) where M is the number of rows in box
     * and N is the number of columns in box.
     */
    fun rotateTheBox(box: Array<CharArray>): Array<CharArray> {
        val (stone, obstacle, empty) = charArrayOf('#', '*', '.')
        val m = box.size
        val n = box[0].size
        val result = Array(n) { CharArray(m) { empty } }

        for (colAfter in 0..<m) {
            var baseRow = n
            for (rowAfter in n - 1 downTo 0) {
                when (beforeRotate(box, rowAfter, colAfter)) {
                    obstacle -> {
                        baseRow = rowAfter
                        result[rowAfter][colAfter] = obstacle
                    }

                    stone -> {
                        baseRow -= 1
                        result[baseRow][colAfter] = stone
                    }

                    empty -> continue
                    else -> throw IllegalArgumentException("Unexpected item")
                }
            }
        }
        return result
    }

    private fun beforeRotate(
        box: Array<CharArray>,
        rowAfter: Int,
        colAfter: Int,
    ): Char = box[box.lastIndex - colAfter][rowAfter]
}
