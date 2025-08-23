package com.hj.leetcode.kotlin.problem3197

/**
 * LeetCode page: [3197. Find the Minimum Area to Cover All Ones II](https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-ii/);
 */
class Solution {
    // Complexity:
    // Time O((M+N)^2) and Space O(MN) where M and N are the number
    // of rows and columns in grid, respectively.
    fun minimumSum(grid: Array<IntArray>): Int {
        // The idea is to brute force. We cut the whole grid
        // into three rectangles, by which we separate the ones
        // into three groups.
        //
        // Pattern 1    Pattern 2    Pattern 3
        //  _______      _______      _______
        // |  | |  |    |  |____|    |____|  |
        // |  | |  |    |  |    |    |    |  |
        // |__|_|__|    |__|____|    |____|__|
        //
        // Pattern 4    Pattern 5    Pattern 6
        //  _______      _______      _______
        // |_______|    |  |    |    |_______|
        // |_______|    |__|____|    |    |  |
        // |_______|    |_______|    |____|__|
        return minOf(solvePattern123(grid), solvePattern123(transpose(grid)))
    }

    private fun transpose(grid: Array<IntArray>): Array<IntArray> {
        val result = Array(grid[0].size) { IntArray(grid.size) }
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                result[c][r] = grid[r][c]
            }
        }
        return result
    }

    private fun solvePattern123(grid: Array<IntArray>): Int {
        // vertExtents[c]:= the first and the last row index
        // of 1 in grid[:][c].
        val vertExtents = calcVertExtents(grid)

        // prefixMinAreas[c]:= the area of minimum rectangle
        // to enclose all 1s in grid[:][..=c].
        val prefixMinAreas = calcPrefixMinAreas(vertExtents)

        // suffixMinAreas[c]:= the area of minimum rectangle
        // to enclose all 1s in grid[:][c:].
        val suffixMinAreas = calcSuffixMinAreas(vertExtents)

        return minOf(
            solvePattern1(vertExtents, prefixMinAreas, suffixMinAreas),
            solvePattern2(grid, vertExtents, prefixMinAreas, suffixMinAreas),
            solvePattern3(grid, vertExtents, prefixMinAreas, suffixMinAreas),
        )
    }

    private fun calcVertExtents(grid: Array<IntArray>): Array<IntArray> {
        val result = Array(grid[0].size) { intArrayOf(grid.size, -1) }
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 1) {
                    result[c][0] = minOf(result[c][0], r)
                    result[c][1] = maxOf(result[c][1], r)
                }
            }
        }
        return result
    }

    private fun calcPrefixMinAreas(vertExtents: Array<IntArray>): IntArray {
        val result = IntArray(vertExtents.size)
        var left = 0
        while (vertExtents[left][1] == -1) {
            left++
        }
        var top = vertExtents[left][0]
        var bottom = vertExtents[left][1]
        for (right in left..<vertExtents.size) {
            if (vertExtents[right][1] == -1) {
                result[right] = result[right - 1]
                continue
            }
            top = minOf(top, vertExtents[right][0])
            bottom = maxOf(bottom, vertExtents[right][1])
            result[right] = (bottom - top + 1) * (right - left + 1)
        }
        return result
    }

    private fun calcSuffixMinAreas(vertExtents: Array<IntArray>): IntArray {
        val result = IntArray(vertExtents.size)
        var right = vertExtents.size - 1
        while (vertExtents[right][1] == -1) {
            right--
        }
        var top = vertExtents[right][0]
        var bottom = vertExtents[right][1]
        for (left in right downTo 0) {
            if (vertExtents[left][1] == -1) {
                result[left] = result[left + 1]
                continue
            }
            top = minOf(top, vertExtents[left][0])
            bottom = maxOf(bottom, vertExtents[left][1])
            result[left] = (bottom - top + 1) * (right - left + 1)
        }
        return result
    }

    private fun solvePattern1(
        vertExtents: Array<IntArray>,
        prefixMinAreas: IntArray,
        suffixMinAreas: IntArray,
    ): Int {
        var result = suffixMinAreas[0]
        var vCut1 = 0
        while (vCut1 < vertExtents.size - 2) {
            if (vertExtents[vCut1][1] == -1) {
                vCut1++
                continue
            }
            if (suffixMinAreas[vCut1 + 1] == 0) {
                break
            }

            var left = vCut1 + 1
            while (vertExtents[left][1] == -1) {
                left++
            }
            var top = vertExtents[left][0]
            var bottom = vertExtents[left][1]
            for (vCut2 in left..<vertExtents.size - 1) {
                if (vertExtents[vCut2][1] == -1) {
                    continue
                }
                if (suffixMinAreas[vCut2 + 1] == 0) {
                    break
                }
                top = minOf(top, vertExtents[vCut2][0])
                bottom = maxOf(bottom, vertExtents[vCut2][1])
                val midArea = (bottom - top + 1) * (vCut2 - left + 1)
                val sumArea = prefixMinAreas[vCut1] + midArea + suffixMinAreas[vCut2 + 1]
                result = minOf(result, sumArea)
            }
            vCut1 = left
        }
        return result
    }

    private fun solvePattern2(
        grid: Array<IntArray>,
        vertExtents: Array<IntArray>,
        prefixMinAreas: IntArray,
        suffixMinAreas: IntArray,
    ): Int {
        // suffixHoriExtents[c][r]:= the first and the last column
        // index of 1 in grid[r][c:].
        val suffixHoriExtents = calcSuffixHoriExtents(grid)

        var result = suffixMinAreas[0]
        for (vCut in 0..<grid[0].size - 1) {
            if (vertExtents[vCut][1] == -1) {
                continue
            }
            if (suffixMinAreas[vCut + 1] == 0) {
                break
            }
            // horiExtents[r]:= the first and the last column index
            // of 1 in grid[r][vCut+1:].
            val horiExtents = suffixHoriExtents[vCut + 1]
            // bottomMinAreas[top]:= the area of minimum rectangle
            // to enclose all 1s in grid[top:][vCut+1:].
            val bottomMinAreas = calcBottomMinAreas(horiExtents)

            var top = 0
            while (horiExtents[top][1] == -1) {
                top++
            }
            var left = horiExtents[top][0]
            var right = horiExtents[top][1]
            for (hCut in top..<grid.size - 1) {
                if (horiExtents[hCut][1] == -1) {
                    continue
                }
                if (bottomMinAreas[hCut + 1] == 0) {
                    break
                }
                left = minOf(left, horiExtents[hCut][0])
                right = maxOf(right, horiExtents[hCut][1])
                val topArea = (hCut - top + 1) * (right - left + 1)
                val sumArea = prefixMinAreas[vCut] + topArea + bottomMinAreas[hCut + 1]
                result = minOf(result, sumArea)
            }
        }
        return result
    }

    private fun calcSuffixHoriExtents(grid: Array<IntArray>): Array<Array<IntArray>> {
        val result =
            Array(grid[0].size) {
                Array(grid.size) { intArrayOf(grid.size, -1) }
            }
        for (r in grid.indices) {
            var right = grid[0].size - 1
            while (right > 0 && grid[r][right] == 0) {
                right--
            }
            if (right == -1) {
                continue
            }

            for (left in right downTo 0) {
                if (grid[r][left] == 0) {
                    result[left][r][0] = result[left + 1][r][0]
                    result[left][r][1] = result[left + 1][r][1]
                } else {
                    result[left][r][0] = left
                    result[left][r][1] = right
                }
            }
        }
        return result
    }

    private fun calcBottomMinAreas(horiExtents: Array<IntArray>): IntArray {
        val result = IntArray(horiExtents.size)
        var bottom = horiExtents.size - 1
        while (horiExtents[bottom][1] == -1) {
            bottom--
        }
        var left = horiExtents[bottom][0]
        var right = horiExtents[bottom][1]
        for (top in bottom downTo 0) {
            if (horiExtents[top][1] == -1) {
                result[top] = result[top + 1]
                continue
            }
            left = minOf(left, horiExtents[top][0])
            right = maxOf(right, horiExtents[top][1])
            result[top] = (bottom - top + 1) * (right - left + 1)
        }
        return result
    }

    private fun solvePattern3(
        grid: Array<IntArray>,
        vertExtents: Array<IntArray>,
        prefixMinAreas: IntArray,
        suffixMinAreas: IntArray,
    ): Int {
        // prefixHoriExtents[c][r]:= the first and the last column
        // index of 1 in grid[r][c:].
        val prefixHoriExtents = calcPrefixHoriExtents(grid)

        var result = suffixMinAreas[0]
        for (vCut in grid[0].size - 1 downTo 1) {
            if (vertExtents[vCut][1] == -1) {
                continue
            }
            if (prefixMinAreas[vCut - 1] == 0) {
                break
            }
            // horiExtents[r]:= the first and the last column index
            // of 1 in grid[r][..<vCut].
            val horiExtents = prefixHoriExtents[vCut - 1]
            // bottomMinAreas[top]:= the area of minimum rectangle
            // to enclose all 1s in grid[top:][..<vCut].
            val bottomMinAreas = calcBottomMinAreas(horiExtents)

            var top = 0
            while (horiExtents[top][1] == -1) {
                top++
            }
            var left = grid[0].size
            var right = -1
            for (hCut in top..<grid.size - 1) {
                if (horiExtents[hCut][1] == -1) {
                    continue
                }
                if (bottomMinAreas[hCut + 1] == 0) {
                    break
                }
                left = minOf(left, horiExtents[hCut][0])
                right = maxOf(right, horiExtents[hCut][1])
                val topArea = (hCut - top + 1) * (right - left + 1)
                val sumArea = suffixMinAreas[vCut] + topArea + bottomMinAreas[hCut + 1]
                result = minOf(result, sumArea)
            }
        }
        return result
    }

    private fun calcPrefixHoriExtents(grid: Array<IntArray>): Array<Array<IntArray>> {
        val result =
            Array(grid[0].size) {
                Array(grid.size) { intArrayOf(grid.size, -1) }
            }
        for (r in grid.indices) {
            var left = 0
            while (left < grid[0].size && grid[r][left] == 0) {
                left++
            }
            if (left == grid[0].size) {
                continue
            }

            for (right in left..<grid[0].size) {
                if (grid[r][right] == 0) {
                    result[right][r][0] = result[right - 1][r][0]
                    result[right][r][1] = result[right - 1][r][1]
                } else {
                    result[right][r][0] = left
                    result[right][r][1] = right
                }
            }
        }
        return result
    }
}
