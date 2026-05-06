package com.hj.leetcode.kotlin.problem1861

/**
 * LeetCode page: [1861. Rotating the Box](https://leetcode.com/problems/rotating-the-box/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Auxiliary Space O(1) where M is the number of rows in box
    // and N is the number of columns in box.
    fun rotateTheBox(box: Array<CharArray>): Array<CharArray> {
        val (stone, obstacle, empty) = charArrayOf('#', '*', '.')
        val m = box.size
        val n = box[0].size
        val rotatedBox = Array(n) { CharArray(m) { empty } }

        for (c in rotatedBox[0].indices) {
            var base = n // the row index to support the next stone

            for (r in rotatedBox.indices.reversed()) {
                val oldContent = box[m - 1 - c][r]
                when (oldContent) {
                    empty -> {
                        continue
                    }

                    stone -> {
                        base -= 1
                        rotatedBox[base][c] = stone
                    }

                    obstacle -> {
                        base = r
                        rotatedBox[base][c] = obstacle
                    }

                    else -> {
                        throw IllegalArgumentException("Unexpected item.")
                    }
                }
            }
        }

        return rotatedBox
    }
}
