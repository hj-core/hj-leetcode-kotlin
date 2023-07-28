package com.hj.leetcode.kotlin.problem486

/**
 * LeetCode page: [486. Predict the Winner](https://leetcode.com/problems/predict-the-winner/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the size of nums;
     */
    fun PredictTheWinner(nums: IntArray): Boolean {
        if (nums.size.isEven()) {
            return true
        }

        val prefixSum = nums.prefixSum()
        val player1Score = optimalScore(nums, nums.indices, prefixSum)
        val player2Score = prefixSum.last() - player1Score
        return player1Score >= player2Score
    }

    private fun Int.isEven(): Boolean = this and 1 == 0

    private fun IntArray.prefixSum(): IntArray {
        val result = this.clone()
        for (index in 1 until result.size) {
            result[index] += result[index - 1]
        }
        return result
    }

    private fun optimalScore(
        nums: IntArray,
        indexRange: IntRange,
        prefixSum: IntArray,
        memoization: MutableMap<IntRange, Int> = hashMapOf()
    ): Int {
        if (indexRange in memoization) {
            return checkNotNull(memoization[indexRange])
        }
        if (indexRange.last == indexRange.first) {
            return nums[indexRange.first]
        }

        val result = sumRange(indexRange, prefixSum) - minOf(
            optimalScore(nums, indexRange.first until indexRange.last, prefixSum, memoization),
            optimalScore(nums, (indexRange.first + 1)..indexRange.last, prefixSum, memoization)
        )
        memoization[indexRange] = result
        return result
    }

    private fun sumRange(indexRange: IntRange, prefixSum: IntArray): Int {
        return if (indexRange.first > 0) {
            prefixSum[indexRange.last] - prefixSum[indexRange.first - 1]
        } else {
            prefixSum[indexRange.last]
        }
    }
}