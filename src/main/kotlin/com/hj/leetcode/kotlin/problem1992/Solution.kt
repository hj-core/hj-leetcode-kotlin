package com.hj.leetcode.kotlin.problem1992

/**
 * LeetCode page: [1992. Find All Groups of Farmland](https://leetcode.com/problems/find-all-groups-of-farmland/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(N) where M is the height of land
     * and N is the width of land;
     */
    fun findFarmland(land: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        val visitedBottom = IntArray(land[0].size) { -1 }
        for (top in land.indices) {
            for (left in land[top].indices) {
                if (land[top][left] == 0 || top <= visitedBottom[left]) {
                    continue
                }
                result.add(dfs(land, top, left, visitedBottom))
            }
        }
        return result.toTypedArray()
    }

    private fun dfs(
        land: Array<IntArray>,
        top: Int,
        left: Int,
        visitedBottom: IntArray,
    ): IntArray {
        var right = left
        while (right < land[top].size && land[top][right] == 1) {
            right++
        }
        right--

        var bottom = top
        while (bottom < land.size && land[bottom][right] == 1) {
            bottom++
        }
        bottom--

        for (column in left..right) {
            visitedBottom[column] = bottom
        }
        return intArrayOf(top, left, bottom, right)
    }
}