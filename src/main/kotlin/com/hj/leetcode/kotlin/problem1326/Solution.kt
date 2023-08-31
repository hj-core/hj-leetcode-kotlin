package com.hj.leetcode.kotlin.problem1326

/**
 * LeetCode page: [1326. Minimum Number of Taps to Open to Water a Garden](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/);
 */
class Solution {
    /* Complexity:
     * Time O(nLog(n)) and Space O(n);
     */
    fun minTaps(n: Int, ranges: IntArray): Int {
        val sortedCoverages = sortedCoverages(ranges)
        var result = 0
        var rightmostCovered = 0
        var coverageIndex = 0

        while (rightmostCovered < n) {
            var optimalCoverage = Coverage(rightmostCovered, rightmostCovered)

            while (coverageIndex < sortedCoverages.size
                && sortedCoverages[coverageIndex].left <= rightmostCovered
            ) {
                if (sortedCoverages[coverageIndex].right > optimalCoverage.right) {
                    optimalCoverage = sortedCoverages[coverageIndex]
                }
                coverageIndex++
            }

            if (optimalCoverage.left == optimalCoverage.right) {
                return -1
            }

            rightmostCovered = optimalCoverage.right
            result++
        }
        return result
    }

    private fun sortedCoverages(ranges: IntArray): List<Coverage> {
        val result = mutableListOf<Coverage>()
        for ((i, range) in ranges.withIndex()) {
            if (range == 0) {
                continue
            }
            result.add(Coverage(i - range, i + range))
        }
        result.sortBy { it.left }
        return result
    }

    private data class Coverage(val left: Int, val right: Int)
}