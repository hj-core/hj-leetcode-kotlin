package com.hj.leetcode.kotlin.problem1770

/**
 * LeetCode page: [1770. Maximum Score from Performing Multiplication Operations](https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/);
 */
class Solution {
    /* Complexity:
     * Time O(M^2) and Space O(M^2) where M is the size of multipliers;
     */
    fun maximumScore(nums: IntArray, multipliers: IntArray): Int {
        val totalSteps = multipliers.size
        val maxScoreDp = getMaxScoreEachStepEachLeftSteps(nums, multipliers)
        return getMaxScore(totalSteps, maxScoreDp)
    }

    private fun getMaxScoreEachStepEachLeftSteps(nums: IntArray, multipliers: IntArray): List<List<Int>> {
        val totalSteps = multipliers.size
        val container = getContainer(totalSteps)

        container[0][0] = 0
        for (step in 1..totalSteps) {
            updateMaxScoreOfEachLeftSteps(container, step, nums, multipliers)
        }
        return container
    }

    private fun getContainer(totalSteps: Int): List<MutableList<Int>> {
        val includeZeroStep = totalSteps + 1
        val container = List(includeZeroStep) { maxLeftSteps ->
            val includeZeroLeftStep = maxLeftSteps + 1
            MutableList(includeZeroLeftStep) { 0 }
        }
        return container
    }

    private fun updateMaxScoreOfEachLeftSteps(
        container:List<MutableList<Int>>,
        step: Int,
        nums: IntArray,
        multipliers: IntArray
    ) {
        val multiplier = getMultiplier(step, multipliers)

        val scoreWhenAllStepsIsRight = container[step - 1][0] + multiplier * getRightNumber(step, nums)
        container[step][0] = scoreWhenAllStepsIsRight

        val scoreWhenAllStepsIsLeft = container[step - 1][step - 1] + multiplier * getLeftNumber(step, nums)
        container[step][step] = scoreWhenAllStepsIsLeft

        for (leftSteps in 1 until step) {
            val rightSteps = getRightSteps(step, leftSteps)
            val maxScoreIfCurrStepIsRight =
                container[step - 1][leftSteps] + multiplier * getRightNumber(rightSteps, nums)

            val maxScoreIfCurrStepIsLeft =
                container[step - 1][leftSteps - 1] + multiplier * getLeftNumber(leftSteps, nums)

            val maxScore = maxOf(maxScoreIfCurrStepIsRight, maxScoreIfCurrStepIsLeft)
            container[step][leftSteps] = maxScore
        }
    }

    private fun getMultiplier(step: Int, multipliers: IntArray) = multipliers[step - 1]

    private fun getRightNumber(rightSteps: Int, nums: IntArray) = nums[nums.size - rightSteps]

    private fun getLeftNumber(leftSteps: Int, nums: IntArray) = nums[leftSteps - 1]

    private fun getRightSteps(totalSteps: Int, leftSteps: Int) = totalSteps - leftSteps

    private fun getMaxScore(step: Int, maxScoreEachStepEachLeftStep: List<List<Int>>) =
        maxScoreEachStepEachLeftStep[step].max()!!
}