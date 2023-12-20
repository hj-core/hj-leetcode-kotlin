package com.hj.leetcode.kotlin.problem661

/**
 * LeetCode page: [661. Image Smoother](https://leetcode.com/problems/image-smoother/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the total number of cells in img;
     */
    fun imageSmoother(img: Array<IntArray>): Array<IntArray> {
        return Array(img.size) { row ->
            IntArray(img[row].size) { column ->
                smoothedPixel(img, row, column)
            }
        }
    }

    private fun smoothedPixel(
        img: Array<IntArray>,
        row: Int,
        column: Int,
    ): Int {

        val shifts = listOf(-1, 0, 1)
        var sum = 0
        var count = 0

        for (rowShift in shifts) {
            val shiftedRow = row + rowShift
            if (shiftedRow !in img.indices) {
                continue
            }

            for (columnShift in shifts) {
                val shiftedColumn = column + columnShift
                if (shiftedColumn !in img[shiftedRow].indices) {
                    continue
                }
                sum += img[shiftedRow][shiftedColumn]
                count++
            }
        }
        return sum / count
    }
}