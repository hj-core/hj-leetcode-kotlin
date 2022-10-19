package com.hj.leetcode.kotlin.problem18

/**
 * LeetCode page: [18. 4Sum](https://leetcode.com/problems/4sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N^3), Aux_Space O(N) and Space O(N^3) where N is the size of nums;
     */
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val sorted = nums.clone().apply { sort() }
        val solver = KSumSolver.newInstance(sorted, 4, target)
        return solver.solve()
    }

    private class KSumSolver private constructor(
        private val sorted: IntArray,
        private val k: Int,
        private val target: Long
    ) {
        fun solve(): List<List<Int>> {
            return solveHelper(0, k, target)
        }

        private fun solveHelper(startIndex: Int, k: Int, target: Long): List<List<Int>> {
            if (k == 2) return solveTwoSum(startIndex, target)

            val isOutOfRange = isTargetOutOfRange(sorted[startIndex], sorted.last(), target, k)
            if (isOutOfRange) return emptyList()

            val result = mutableListOf<List<Int>>()

            for (index in startIndex..sorted.size - k) {
                val shouldSkip = index != startIndex && sorted[index] == sorted[index - 1]
                if (shouldSkip) continue

                val num = sorted[index]
                val complementary = target - num
                val complementaryResult = solveHelper(index + 1, k - 1, complementary)

                for (list in complementaryResult) {
                    result.add(list + num)
                }
            }

            return result
        }

        private fun solveTwoSum(startIndex: Int, target: Long): List<List<Int>> {
            val noEnoughElements = sorted.size - startIndex < 2
            if (noEnoughElements) return emptyList()

            val isOutOfRange = isTargetOutOfRange(sorted[startIndex], sorted.last(), target, 2)
            if (isOutOfRange) return emptyList()

            val result = mutableListOf<List<Int>>()
            var leftIndex = startIndex
            var rightIndex = findRightIndex(leftIndex, target)

            while (leftIndex < rightIndex) {
                val sum = sorted[leftIndex] + sorted[rightIndex]

                when {
                    sum < target -> leftIndex = toNextLeftIndex(leftIndex, rightIndex)
                    sum > target -> rightIndex = toNextRightIndex(leftIndex, rightIndex)

                    else -> {
                        result.add(listOf(sorted[leftIndex], sorted[rightIndex]))
                        leftIndex = toNextLeftIndex(leftIndex, rightIndex)
                        rightIndex = toNextRightIndex(leftIndex, rightIndex)
                    }
                }
            }

            return result
        }

        private fun isTargetOutOfRange(minNum: Int, maxNum: Int, target: Long, k: Int): Boolean {
            val average = target / k
            return minNum > average || maxNum < average
        }

        private fun findRightIndex(leftIndex: Int, target: Long): Int {
            val complementary = (target - sorted[leftIndex]).toInt()
            return sorted
                .binarySearch(complementary, leftIndex + 1)
                .let { insertionIndex -> if (insertionIndex < 0) -(insertionIndex + 1) else insertionIndex }
                .coerceAtMost(sorted.lastIndex)
        }

        private fun toNextLeftIndex(leftIndex: Int, rightIndex: Int): Int {
            var nextLeftIndex = leftIndex + 1

            while (sorted[nextLeftIndex] == sorted[nextLeftIndex - 1] && nextLeftIndex < rightIndex) {
                nextLeftIndex++
            }
            return nextLeftIndex
        }

        private fun toNextRightIndex(leftIndex: Int, rightIndex: Int): Int {
            var nextRightIndex = rightIndex - 1

            while (sorted[nextRightIndex] == sorted[nextRightIndex + 1] && nextRightIndex > leftIndex) {
                nextRightIndex--
            }
            return nextRightIndex
        }

        companion object {
            fun newInstance(sorted: IntArray, k: Int, target: Int): KSumSolver {
                require(k > 1)
                return KSumSolver(sorted, k, target.toLong())
            }
        }
    }
}