package com.hj.leetcode.kotlin.problem2191

/**
 * LeetCode page: [2191. Sort the Jumbled Numbers](https://leetcode.com/problems/sort-the-jumbled-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums;
     */
    fun sortJumbled(mapping: IntArray, nums: IntArray): IntArray {
        val mappedValues = nums.map { mapValue(it, mapping) }
        val sortedIndices = nums
            .indices
            .sortedWith(compareBy({ mappedValues[it] }, { it }))

        return buildResult(nums, sortedIndices)
    }

    private fun mapValue(value: Int, mapping: IntArray): Int {
        if (value == 0) {
            return mapping[0]
        }

        var result = 0
        var unit = 1
        var num = value
        while (num > 0) {
            result += mapping[num % 10] * unit
            unit *= 10
            num /= 10
        }
        return result
    }

    private fun buildResult(nums: IntArray, sortedIndices: List<Int>): IntArray {
        val result = IntArray(nums.size)
        for (i in result.indices) {
            result[i] = nums[sortedIndices[i]]
        }
        return result
    }
}