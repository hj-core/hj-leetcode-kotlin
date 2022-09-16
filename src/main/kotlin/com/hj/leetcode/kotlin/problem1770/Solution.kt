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
        val maxScoreDp = getMaxScoreEachStepEachLeftStep(nums, multipliers)
        return getMaxScore(totalSteps, maxScoreDp)
    }

    private fun getMaxScoreEachStepEachLeftStep(nums: IntArray, multipliers: IntArray): List<List<Int>> {
        val totalSteps = multipliers.size
        val container = getContainerForMaxScoreEachStepEachLeftStep(totalSteps)

        container[0][0] = 0
        for (step in 1..totalSteps) {
            val multiplier = getMultiplier(step, multipliers)

            val scoreWhenAllStepIsRight = container[step - 1][0] + multiplier * getNumberAtRight(step, nums)
            container[step][0] = scoreWhenAllStepIsRight

            val scoreWhenAllStepIsLeft = container[step - 1][step - 1] + multiplier * getNumberAtLeft(step, nums)
            container[step][step] = scoreWhenAllStepIsLeft

            for (leftStep in 1 until step) {
                val maxScoreIfCurrStepIsLeft =
                    container[step - 1][leftStep - 1] + multiplier * getNumberAtLeft(leftStep, nums)

                val rightStep = getRightStep(step, leftStep)
                val maxScoreIfCurrStepIsRight =
                    container[step - 1][leftStep] + multiplier * getNumberAtRight(rightStep, nums)

                container[step][leftStep] = maxOf(maxScoreIfCurrStepIsLeft, maxScoreIfCurrStepIsRight)
            }
        }
        return container
    }

    private fun getContainerForMaxScoreEachStepEachLeftStep(totalSteps: Int): List<MutableList<Int>> {
        val sizeIncludeZeroStep = totalSteps + 1
        val container = List(sizeIncludeZeroStep) { maxLeftStep ->
            val sizeIncludeZeroLeftStep = maxLeftStep + 1
            MutableList(sizeIncludeZeroLeftStep) { 0 }
        }
        return container
    }

    private fun getMultiplier(step: Int, multipliers: IntArray) = multipliers[step - 1]

    private fun getNumberAtRight(rightStep: Int, nums: IntArray) = nums[nums.size - rightStep]

    private fun getNumberAtLeft(leftStep: Int, nums: IntArray) = nums[leftStep - 1]

    private fun getRightStep(step: Int, leftStep: Int) = step - leftStep

    private fun getMaxScore(totalSteps: Int, maxScoreEachStepEachLeftStep: List<List<Int>>) =
        maxScoreEachStepEachLeftStep[totalSteps].max()!!
}