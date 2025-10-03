package com.hj.leetcode.kotlin.problem407

/**
 * LeetCode page: [407. Trapping Rain Water II](https://leetcode.com/problems/trapping-rain-water-ii/);
 */
class Solution3 {
    // Complexity:
    // Time O(???) and Space O(MN) where M and N are the number
    // of rows and columns in heightMap, respectively.
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        val m = heightMap.size
        val n = heightMap[0].size

        if (m < 3 || n < 3) {
            return 0
        }

        // dp[r][c]:= the water height at cell(r, c)
        val dp = newInitDp(heightMap)
        while (!update(dp, heightMap)) {
        }

        return calcVolume(heightMap, dp)
    }

    // Returns a new DP array in which the boundary cells have
    // the same height as heightMap, while the inner cells have
    // a value of Int.MAX_VALUE.
    private fun newInitDp(heightMap: Array<IntArray>): Array<IntArray> {
        val m = heightMap.size
        val n = heightMap[0].size

        val result = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        heightMap[0].copyInto(result[0])
        heightMap[m - 1].copyInto(result[m - 1])

        for (r in 1..<m - 1) {
            result[r][0] = heightMap[r][0]
            result[r][n - 1] = heightMap[r][n - 1]
        }
        return result
    }

    // Updates the DP array and returns true if a stable state
    // is reached.
    private fun update(
        dp: Array<IntArray>,
        heightMap: Array<IntArray>,
    ): Boolean {
        val m = heightMap.size
        val n = heightMap[0].size

        var stable = true
        for (r1 in 1..<m - 1) {
            for (c1 in 1..<n - 1) {
                val newHeight =
                    minOf(dp[r1][c1], dp[r1 - 1][c1], dp[r1][c1 - 1])
                        .coerceAtLeast(heightMap[r1][c1])

                if (dp[r1][c1] != newHeight) {
                    stable = false
                    dp[r1][c1] = newHeight
                }
            }

            val r2 = m - 1 - r1
            for (c2 in n - 2 downTo 1) {
                val newHeight =
                    minOf(dp[r2][c2], dp[r2 + 1][c2], dp[r2][c2 + 1])
                        .coerceAtLeast(heightMap[r2][c2])

                if (dp[r2][c2] != newHeight) {
                    stable = false
                    dp[r2][c2] = newHeight
                }
            }
        }

        return stable
    }

    private fun calcVolume(
        heightMap: Array<IntArray>,
        waterHeights: Array<IntArray>,
    ): Int {
        var result = 0
        for (r in 1..<heightMap.size - 1) {
            for (c in 1..<heightMap[r].size - 1) {
                result += waterHeights[r][c] - heightMap[r][c]
            }
        }
        return result
    }
}
