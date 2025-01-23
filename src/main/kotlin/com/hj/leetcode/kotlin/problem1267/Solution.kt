package com.hj.leetcode.kotlin.problem1267

/**
 * LeetCode page: [1267. Count Servers that Communicate](https://leetcode.com/problems/count-servers-that-communicate/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(M+N)
     * where M and N are the number of rows and columns in grid, respectively.
     */
    fun countServers(grid: Array<IntArray>): Int {
        val trackRow = IntArray(grid.size)
        val trackCol = IntArray(grid[0].size)
        var serverCnt = 0
        var singleCnt = 0
        val signBit = 1 shl 31

        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (!isServer(grid[row][col])) {
                    continue
                }
                serverCnt++

                if (trackRow[row] == 0 && trackCol[col] == 0) {
                    singleCnt++
                    trackRow[row] = col xor signBit
                    trackCol[col] = row xor signBit
                    continue
                }

                if (trackRow[row] < 0) {
                    val colSingle = trackRow[row] xor signBit
                    trackCol[colSingle] = 1
                    singleCnt--
                }
                trackRow[row] = 1

                if (trackCol[col] < 0) {
                    val rowSingle = trackCol[col] xor signBit
                    trackRow[rowSingle] = 1
                    singleCnt--
                }
                trackCol[col] = 1
            }
        }
        return serverCnt - singleCnt
    }

    private fun isServer(value: Int): Boolean = value == 1
}
