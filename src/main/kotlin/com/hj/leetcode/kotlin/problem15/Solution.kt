package com.hj.leetcode.kotlin.problem15

/**
 * LeetCode page: [15. 3Sum](https://leetcode.com/problems/3sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of nums;
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        val sortedNumbers = getSortedNumbers(nums)
        return getValidTriplets(sortedNumbers)
    }

    private fun getSortedNumbers(numbers: IntArray): IntArray {
        return numbers.clone().apply { sort() }
    }

    private fun getValidTriplets(sortedNumbers: IntArray): List<List<Int>> {
        if (sortedNumbers.first() > 0 || sortedNumbers.last() < 0) return emptyList()

        val leftBound = getLeftBoundIndex(sortedNumbers)
        val rightBound = getRightBoundIndex(sortedNumbers)

        val validTriplets = hashSetOf<List<Int>>()
        val availableThirdNumbers = hashSetOf<Int>().also { it.add(sortedNumbers[leftBound]) }
        for (firstIndex in leftBound + 1 until rightBound) {
            val firstNumber = sortedNumbers[firstIndex]
            for (secondIndex in firstIndex + 1..rightBound) {
                val secondNumber = sortedNumbers[secondIndex]
                val target = -(firstNumber + secondNumber)
                if (target in availableThirdNumbers) validTriplets.add(listOf(firstNumber, secondNumber, target))
            }
            availableThirdNumbers.add(sortedNumbers[firstIndex])
        }
        return validTriplets.toList()
    }

    private fun getLeftBoundIndex(sortedNumbers: IntArray): Int {
        val maxTwoSum = sortedNumbers
            .lastIndex
            .let { sortedNumbers[it] + sortedNumbers[it - 1] }
        return sortedNumbers
            .binarySearch(-maxTwoSum, 0, sortedNumbers.lastIndex - 1)
            .let { biIndex -> if (biIndex < 0) -(biIndex + 1) else biIndex }
    }

    private fun getRightBoundIndex(sortedNumbers: IntArray): Int {
        val minTwoSum = sortedNumbers[0] + sortedNumbers[1]
        return sortedNumbers
            .binarySearch(-minTwoSum, 2, sortedNumbers.size)
            .let { biIndex -> if (biIndex < 0) -(biIndex + 1) else biIndex }
            .coerceAtMost(sortedNumbers.lastIndex)
    }
}