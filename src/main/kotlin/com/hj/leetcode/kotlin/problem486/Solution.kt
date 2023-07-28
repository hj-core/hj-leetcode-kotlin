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
        return maxScoreLead(nums) >= 0
    }

    private fun Int.isEven(): Boolean = this and 1 == 0

    private fun maxScoreLead(
        nums: IntArray,
        gameRange: IntRange = nums.indices,
        memoization: MutableMap<IntRange, Int> = hashMapOf()
    ): Int {
        if (gameRange in memoization) {
            return checkNotNull(memoization[gameRange])
        }
        with(gameRange) {
            if (first == last) {
                return nums[first]
            }
            val result = maxOf(
                nums[first] - maxScoreLead(nums, (first + 1)..last, memoization),
                nums[last] - maxScoreLead(nums, first until last, memoization)
            )
            memoization[gameRange] = result
            return result
        }
    }
}