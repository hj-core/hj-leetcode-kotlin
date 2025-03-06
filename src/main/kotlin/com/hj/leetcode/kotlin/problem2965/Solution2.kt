package com.hj.leetcode.kotlin.problem2965

/**
 * LeetCode page: [2965. Find Missing and Repeated Values](https://leetcode.com/problems/find-missing-and-repeated-values/);
 */
class Solution2 {
    // Complexity:
    // Time O(N^2) and Space O(1) where N is the length of grid.
    fun findMissingAndRepeatedValues(grid: Array<IntArray>): IntArray {
        val n = grid.size
        // Denote the duplicated number as a and the missing number as b.
        // Find a bit mask that can be used to distinguish between a and b.
        var aXorB = 0
        for (row in grid) {
            for (value in row) {
                aXorB = aXorB xor value
            }
        }
        for (value in 1..n * n) {
            aXorB = aXorB xor value
        }
        check(aXorB != 0) { "a cannot equal b" }
        val bitMask = aXorB.takeLowestOneBit()

        // Xor all the numbers in the grid and all the numbers from 1 to n^2 that have the bitMask set.
        // This gives the value of either a or b; let's call it c.
        var c = 0
        for (row in grid) {
            for (value in row) {
                if (value and bitMask != 0) {
                    c = c xor value
                }
            }
        }
        for (value in 1..n * n) {
            if (value and bitMask != 0) {
                c = c xor value
            }
        }

        // Return result based on whether c is in the grid
        for (row in grid) {
            for (value in row) {
                if (value == c) {
                    return intArrayOf(c, c xor aXorB)
                }
            }
        }
        return intArrayOf(c xor aXorB, c)
    }
}
