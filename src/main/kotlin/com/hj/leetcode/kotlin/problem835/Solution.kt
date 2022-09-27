package com.hj.leetcode.kotlin.problem835

/**
 * LeetCode page: [835. Image Overlap](https://leetcode.com/problems/image-overlap/);
 */
class Solution {
    /* Complexity:
     * Time O(N^4) and Space O(N^2) where N is the size of img1 and img2;
     */
    fun largestOverlap(img1: Array<IntArray>, img2: Array<IntArray>): Int {
        val numOnesInImg1 = countOnes(img1)
        val numOnesInImg2 = countOnes(img2)
        val minNumOnes = minOf(numOnesInImg1, numOnesInImg2)
        if (minNumOnes == 0 || minNumOnes == 1) return minNumOnes

        val (lessContent, moreContent) = if (numOnesInImg1 < numOnesInImg2) img1 to img2 else img2 to img1
        val coordinateOfOnes = findCoordinatesOfOnes(lessContent)

        val size = img1.size
        val possibleShift = -(size - 1) until size
        var largestOverlap = 0

        for (rowShift in possibleShift) {
            for (columnShift in possibleShift) {
                val overlap = countOverlap(moreContent, rowShift, columnShift, coordinateOfOnes)
                largestOverlap = maxOf(largestOverlap, overlap)

                val reachLargestPossible = largestOverlap == minNumOnes
                if (reachLargestPossible) return largestOverlap
            }
        }
        return largestOverlap
    }

    private fun countOnes(image: Array<IntArray>): Int {
        var count = 0

        for (row in image.indices) {
            for (column in image[row].indices) {
                if (image[row][column] == 1) count++
            }
        }
        return count
    }

    private fun findCoordinatesOfOnes(image: Array<IntArray>): List<Coordinates> {
        val coordinates = mutableListOf<Coordinates>()

        for (row in image.indices) {
            for (column in image[row].indices) {
                if (image[row][column] == 1) coordinates.add(Coordinates(row, column))
            }
        }
        return coordinates
    }

    private data class Coordinates(val row: Int, val column: Int)

    private fun countOverlap(
        imageToShift: Array<IntArray>,
        rowShift: Int,
        columnShift: Int,
        coordinateOfOnes: List<Coordinates>
    ): Int {
        var overlap = 0

        for (coordinate in coordinateOfOnes) {
            val isOverlap = with(coordinate) {
                imageToShift.getOrNull(row - rowShift)?.getOrNull(column - columnShift) == 1
            }
            if (isOverlap) overlap++
        }
        return overlap
    }
}