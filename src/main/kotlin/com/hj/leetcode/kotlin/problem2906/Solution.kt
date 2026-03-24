package com.hj.leetcode.kotlin.problem2906

/**
 * LeetCode page: [2906. Construct Product Matrix](https://leetcode.com/problems/construct-product-matrix/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(MN) where M and N are the number of rows and
    // columns in grid, respectively.
    fun constructProductMatrix(grid: Array<IntArray>): Array<IntArray> {
        val modulus = 12345

        // suffixProduct[r][c]:= the product % modulus of numbers after
        // grid[r][c].
        val suffixProduct = Array(grid.size) { r -> IntArray(grid[r].size) }
        var accProduct = 1L
        for (r in grid.indices.reversed()) {
            for (c in grid[r].indices.reversed()) {
                suffixProduct[r][c] = accProduct.toInt()
                accProduct = (accProduct * grid[r][c]) % modulus
            }
        }

        val productMatrix = Array(grid.size) { r -> IntArray(grid[r].size) }
        var prefixProduct = 1L
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                productMatrix[r][c] =
                    ((prefixProduct * suffixProduct[r][c]) % modulus).toInt()
                prefixProduct = (prefixProduct * grid[r][c]) % modulus
            }
        }

        return productMatrix
    }
}
