package com.hj.leetcode.kotlin.problem16

/**
 * LeetCode page: [16. 3Sum Closest](https://leetcode.com/problems/3sum-closest/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of nums;
     */
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        val sorted = nums.clone().apply { sort() }

        val maxSum = sorted.lastIndex.let { sorted[it] + sorted[it - 1] + sorted[it - 2] }
        val numsTooSmall = target >= maxSum
        if (numsTooSmall) return maxSum

        var closestSum = maxSum
        var minDistance = Math.abs(target - closestSum)

        for (firstIndex in 0..sorted.size - 3) {
            val minSum = sorted[firstIndex] + sorted[firstIndex + 1] + sorted[firstIndex + 2]
            val canEndSearch = minSum >= target
            if (canEndSearch) {
                val distance = minSum - target
                closestSum = if (distance < minDistance) minSum else closestSum
                return closestSum
            }

            var secondIndex = firstIndex + 1
            var thirdIndex = getThirdIndex(sorted, firstIndex, secondIndex, target)

            while (secondIndex < thirdIndex) {
                val sum = sorted[firstIndex] + sorted[secondIndex] + sorted[thirdIndex]
                val distance = Math.abs(target - sum)
                if (distance < minDistance) {
                    minDistance = distance
                    closestSum = sum
                }

                when {
                    sum > target -> thirdIndex--
                    sum < target -> secondIndex++
                    else -> return closestSum
                }
            }
        }

        return closestSum
    }

    private fun getThirdIndex(sorted: IntArray, firstIndex: Int, secondIndex: Int, targetSum: Int): Int {
        val targetThird = targetSum - sorted[firstIndex] - sorted[secondIndex]
        return sorted
            .binarySearch(targetThird, secondIndex + 1)
            .let { bsIndex -> if (bsIndex < 0) -(bsIndex + 1) else bsIndex }
            .coerceAtMost(sorted.lastIndex)
    }
}