package com.hj.leetcode.kotlin.problem167

/**
 * LeetCode page: [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of numbers;
     */
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var leftIndex = 0
        var rightIndex = numbers
            .binarySearch(target - numbers[leftIndex])
            .let { insertionIndex -> if (insertionIndex < 0) -(insertionIndex + 1) else insertionIndex }
            .coerceAtMost(numbers.lastIndex)

        if (rightIndex == 0) {
            check(numbers[0] + numbers[1] == target) // Guaranteed by problem constraints
            return intArrayOf(1, 2)
        }

        while (leftIndex < rightIndex) {
            val currSum = numbers[leftIndex] + numbers[rightIndex]

            when {
                currSum < target -> leftIndex++
                currSum > target -> rightIndex--
                else -> return intArrayOf(leftIndex + 1, rightIndex + 1)
            }
        }
        return intArrayOf(-1, -1)
    }
}