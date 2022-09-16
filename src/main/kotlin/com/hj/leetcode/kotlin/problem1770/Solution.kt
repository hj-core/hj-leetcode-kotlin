package com.hj.leetcode.kotlin.problem1770

/**
 * LeetCode page: [1770. Maximum Score from Performing Multiplication Operations](https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/);
 */
class Solution {
    /* Complexity:
     * Time O(M^2) and Space O(M) where M is the size of multipliers;
     */
    fun maximumScore(nums: IntArray, multipliers: IntArray): Int {
        val maxScoreDP = getMaxScoreForEachLeftOperand(nums, multipliers)
        return maxScoreDP.max()!!
    }

    private fun getMaxScoreForEachLeftOperand(nums: IntArray, multipliers: IntArray): IntArray {
        val totalOperand = multipliers.size
        val scoreContainer = createAndInitializeScoreContainer(totalOperand)

        for (operand in 1..totalOperand) {
            updateScoreContainer(operand, scoreContainer, nums, multipliers)
        }
        return scoreContainer
    }

    private fun createAndInitializeScoreContainer(totalOperands: Int): IntArray {
        val sizeIncludeZeroOperand = totalOperands + 1
        return IntArray(sizeIncludeZeroOperand) { 0 }
    }

    private fun updateScoreContainer(
        currOperand: Int,
        scoreContainer: IntArray,
        nums: IntArray,
        multipliers: IntArray
    ) {
        val multiplier = getMultiplier(currOperand, multipliers)
        updateMaxScoreOfPureLeftOperations(currOperand, scoreContainer, nums, multiplier)
        updateMaxScoreOfCompoundOperations(currOperand, scoreContainer, nums, multiplier)
        updateMaxScoreOfPureRightOperations(currOperand, scoreContainer, nums, multiplier)
    }

    private fun getMultiplier(operand: Int, multipliers: IntArray) = multipliers[operand - 1]

    private fun updateMaxScoreOfPureLeftOperations(
        currOperand: Int,
        scoreContainer: IntArray,
        nums: IntArray,
        multiplier: Int
    ) {
        val score = scoreContainer[currOperand - 1] + multiplier * getLeftNumber(currOperand, nums)
        scoreContainer[currOperand] = score
    }

    private fun getLeftNumber(leftOperand: Int, nums: IntArray) = nums[leftOperand - 1]

    private fun updateMaxScoreOfCompoundOperations(
        currOperand: Int,
        scoreContainer: IntArray,
        nums: IntArray,
        multiplier: Int
    ) {
        for (leftOperand in currOperand - 1 downTo 1) {
            val rightOperand = currOperand - leftOperand

            val maxScoreIfCurrOperationIsLeft =
                scoreContainer[leftOperand - 1] + multiplier * getLeftNumber(leftOperand, nums)
            val maxScoreIfCurrOperationIsRight =
                scoreContainer[leftOperand] + multiplier * getRightNumber(rightOperand, nums)

            val maxScore = maxOf(maxScoreIfCurrOperationIsLeft, maxScoreIfCurrOperationIsRight)
            scoreContainer[leftOperand] = maxScore
        }
    }

    private fun getRightNumber(rightOperand: Int, nums: IntArray) = nums[nums.size - rightOperand]

    private fun updateMaxScoreOfPureRightOperations(
        currOperand: Int,
        scoreContainer: IntArray,
        nums: IntArray,
        multiplier: Int
    ) {
        val score = scoreContainer[0] + multiplier * getRightNumber(currOperand, nums)
        scoreContainer[0] = score
    }
}