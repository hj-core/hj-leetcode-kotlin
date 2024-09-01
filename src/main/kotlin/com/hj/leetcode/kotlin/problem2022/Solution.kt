package com.hj.leetcode.kotlin.problem2022

import java.util.*

/**
 * LeetCode page: [2022. Convert 1D Array Into 2D Array](https://leetcode.com/problems/convert-1d-array-into-2d-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of original
     */
    fun construct2DArray(
        original: IntArray,
        m: Int,
        n: Int,
    ): Array<IntArray> {
        if (original.size != m * n) {
            return emptyArray()
        }

        return Array(m) { r ->
            val start = r * n
            Arrays.copyOfRange(original, start, start + n)
        }
    }
}
