package com.hj.leetcode.kotlin.problem15

/**
 * LeetCode page: [15. 3Sum](https://leetcode.com/problems/3sum/);
 */
class Solution {
    private var sortedNumber = intArrayOf()

    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of nums;
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        updateSortedNumber(nums)
        return getValidTriplets()
    }

    private fun updateSortedNumber(numbers: IntArray) {
        sortedNumber = numbers
            .clone()
            .apply { sort() }
    }

    private fun getValidTriplets(): List<List<Int>> {
        if (sortedNumber.first() > 0 || sortedNumber.last() < 0) return emptyList()

        val leftBound = getLeftBoundIndex()
        val rightBound = getRightBoundIndex()

        val validTriplets = hashSetOf<List<Int>>()
        val availableThirdNumbers = hashSetOf<Int>().also { it.add(sortedNumber[leftBound]) }
        for (firstIndex in leftBound + 1 until rightBound) {
            val firstNumber = sortedNumber[firstIndex]
            for (secondIndex in firstIndex + 1..rightBound) {
                val secondNumber = sortedNumber[secondIndex]
                val target = -(firstNumber + secondNumber)
                if (target in availableThirdNumbers) validTriplets.add(listOf(firstNumber, secondNumber, target))
            }
            availableThirdNumbers.add(sortedNumber[firstIndex])
        }

        resetState()
        return validTriplets.toList()
    }

    private fun getLeftBoundIndex(): Int {
        val maxTwoSum = sortedNumber
            .lastIndex
            .let { sortedNumber[it] + sortedNumber[it - 1] }
        return sortedNumber
            .binarySearch(-maxTwoSum, 0, sortedNumber.lastIndex - 1)
            .let { biIndex -> if (biIndex < 0) -(biIndex + 1) else biIndex }
    }

    private fun getRightBoundIndex(): Int {
        val minTwoSum = sortedNumber[0] + sortedNumber[1]
        return sortedNumber
            .binarySearch(-minTwoSum, 2, sortedNumber.size)
            .let { biIndex -> if (biIndex < 0) -(biIndex + 1) else biIndex }
            .coerceAtMost(sortedNumber.lastIndex)
    }

    private fun resetState() {
        sortedNumber = intArrayOf()
    }
}