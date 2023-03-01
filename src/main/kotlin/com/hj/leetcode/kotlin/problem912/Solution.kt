package com.hj.leetcode.kotlin.problem912

/**
 * LeetCode page: [912. Sort an Array](https://leetcode.com/problems/sort-an-array/);
 *
 * TODO : Add Heap Sort solution;
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun sortArray(nums: IntArray): IntArray {
        MergeSort(nums).run()
        return nums
    }

    private class MergeSort(private val original: IntArray) {

        private val elementHolder = IntArray(original.size)

        fun run() {
            splitAndMerge(original.indices, ::mergeToOriginal)
        }

        private fun splitAndMerge(indexRange: IntRange, merge: (indexRange: IntRange) -> Unit) {
            val shouldSplit = indexRange.let { it.first != it.last }
            if (shouldSplit) {
                val midIndex = computeMidIndex(indexRange)
                splitAndMerge(indexRange.first..midIndex, merge)
                splitAndMerge(midIndex + 1..indexRange.last, merge)
            }
            merge(indexRange)
        }

        private fun computeMidIndex(indexRange: IntRange): Int {
            return indexRange.let { (it.first + it.last) ushr 1 }
        }

        private fun mergeToOriginal(indexRange: IntRange) {
            copyCurrentElementsToHolder(indexRange)

            val midIndex = computeMidIndex(indexRange)
            var sorted1Index = indexRange.first
            var sorted2Index = midIndex + 1
            var assignToIndex = indexRange.first

            while (assignToIndex <= indexRange.last) {
                val shouldAdoptSorted1Index = when {
                    sorted1Index > midIndex -> false
                    sorted2Index > indexRange.last -> true
                    else -> elementHolder[sorted1Index] <= elementHolder[sorted2Index]
                }

                if (shouldAdoptSorted1Index) {
                    original[assignToIndex] = elementHolder[sorted1Index]
                    sorted1Index++
                } else {
                    original[assignToIndex] = elementHolder[sorted2Index]
                    sorted2Index++
                }
                assignToIndex++
            }
        }

        private fun copyCurrentElementsToHolder(indexRange: IntRange) {
            for (index in indexRange) {
                elementHolder[index] = original[index]
            }
        }
    }
}