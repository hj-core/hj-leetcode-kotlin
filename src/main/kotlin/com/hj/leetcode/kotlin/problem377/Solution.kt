package com.hj.leetcode.kotlin.problem377

/**
 * LeetCode page: [377. Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/);
 */
class Solution {
    /* Complexity:
     * Time O(N * target) and Space O(target) where N is the size of nums;
     */
    fun combinationSum4(nums: IntArray, target: Int): Int {
        return numCombinations(nums, target, hashMapOf())
    }

    private fun numCombinations(
        nums: IntArray,
        target: Int,
        memoization: MutableMap<Int, Int> // entry = (target, numCombinations)
    ): Int {
        if (target < 0) {
            return 0
        }
        if (target == 0) {
            return 1
        }
        if (target in memoization) {
            return checkNotNull(memoization[target])
        }

        var result = 0
        for (number in nums) {
            result += numCombinations(nums, target - number, memoization)
        }
        memoization[target] = result
        return result
    }
}