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
        val scoreContainer = createScoreContainer(totalOperand)

        updateScoreContainer(scoreContainer, nums, multipliers)
        return scoreContainer
    }

    private fun createScoreContainer(totalOperands: Int): IntArray {
        val sizeIncludeZeroOperand = totalOperands + 1
        return IntArray(sizeIncludeZeroOperand) { 0 }
    }

    private fun updateScoreContainer(
        scoreContainer: IntArray,
        nums: IntArray,
        multipliers: IntArray
    ) {
        val initialScore = 0
        scoreContainer[0] = initialScore

        val totalOperand = multipliers.size
        for (operand in 1..totalOperand) {
            val multiplier = getMultiplier(operand, multipliers)
            updateMaxScoreOfPureLeftOperations(operand, scoreContainer, nums, multiplier)
            updateMaxScoreOfCompoundOperations(operand, scoreContainer, nums, multiplier)
            updateMaxScoreOfPureRightOperations(operand, scoreContainer, nums, multiplier)
        }
    }

    private fun getMultiplier(operand: Int, multipliers: IntArray) = multipliers[operand - 1]

    private fun updateMaxScoreOfPureLeftOperations(
        currOperand: Int,
        scoreContainer: IntArray,
        nums: IntArray,
        multiplier: Int
    ) {
        val scoreOfCurrOperation = multiplier * getLeftNumber(currOperand, nums)
        val maxScore = scoreContainer[currOperand - 1] + scoreOfCurrOperation
        scoreContainer[currOperand] = maxScore
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

            val scoreOfCurrOperationIfIsLeft = multiplier * getLeftNumber(leftOperand, nums)
            val maxScoreIfCurrOperationIsLeft = scoreContainer[leftOperand - 1] + scoreOfCurrOperationIfIsLeft

            val scoreOfCurrOperationIfIsRight = multiplier * getRightNumber(rightOperand, nums)
            val maxScoreIfCurrOperationIsRight = scoreContainer[leftOperand] + scoreOfCurrOperationIfIsRight

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
        val scoreOfCurrOperation = multiplier * getRightNumber(currOperand, nums)
        val maxScore = scoreContainer[0] + scoreOfCurrOperation
        scoreContainer[0] = maxScore
    }
}