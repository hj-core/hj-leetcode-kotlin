package com.hj.leetcode.kotlin.problem835

/**
 * LeetCode page: [835. Image Overlap](https://leetcode.com/problems/image-overlap/);
 */
class Solution {
    // Complexity:
    // Time O(N^3) and Space O(N) where N is the size of img1 and img2.
    fun largestOverlap(
        img1: Array<IntArray>,
        img2: Array<IntArray>,
    ): Int {
        val n = img1.size
        val img1 = toIntArray(img1)
        val img2 = toIntArray(img2)

        var maxOverlap = 0
        for (dr in -(n - 1)..<n) {
            for (dc in -(n - 1)..<n) {
                maxOverlap = maxOf(maxOverlap, countOverlap(img1, img2, dr, dc))
            }
        }

        return maxOverlap
    }

    private fun toIntArray(img: Array<IntArray>): IntArray =
        IntArray(img.size) {
            val row = img[it]
            row.foldIndexed(0) { index, acc, bit ->
                bit shl (row.lastIndex - index) or acc
            }
        }

    private fun countOverlap(
        img1: IntArray,
        img2: IntArray,
        dr: Int,
        dc: Int,
    ): Int {
        val n = img1.size
        val r1Range = if (dr < 0) -dr..<n else 0..<(n - dr)
        var overlap = 0
        for (r1 in r1Range) {
            val newRow1 = if (dc < 0) img1[r1] shl -dc else img1[r1] ushr dc
            val row2 = img2[r1 + dr]
            overlap += (newRow1 and row2).countOneBits()
        }
        return overlap
    }
}
