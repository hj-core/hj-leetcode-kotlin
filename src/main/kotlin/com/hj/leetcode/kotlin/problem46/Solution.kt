package com.hj.leetcode.kotlin.problem46

/**
 * LeetCode page: [46. Permutations](https://leetcode.com/problems/permutations/);
 */
class Solution {
    /* Complexity:
     * Time O(N * N!) and Space O(N * N!) where N is the size of nums;
     */
    fun permute(nums: IntArray): List<List<Int>> {
        val permutes = mutableListOf(mutableListOf(nums[0]))

        for (index in 1..nums.lastIndex) {
            addNumberToEachPermute(nums[index], permutes)
            addRotationOfExistingPermutes(permutes)
        }
        return permutes
    }

    private fun addNumberToEachPermute(newNumber: Int, permutes: MutableList<MutableList<Int>>) {
        for (permute in permutes) {
            permute.add(newNumber)
        }
    }

    private fun addRotationOfExistingPermutes(permutes: MutableList<MutableList<Int>>) {
        repeat(permutes.size) { index ->
            addRotationOfSpecificPermute(index, permutes)
        }
    }

    private fun addRotationOfSpecificPermute(permuteIndex: Int, permutes: MutableList<MutableList<Int>>) {
        val permuteToRotate = permutes[permuteIndex]
        val greatestShift = permuteToRotate.size - 1
        for (shift in 1..greatestShift) {
            permutes.add(mutableListOf())
            for (index in shift..permuteToRotate.lastIndex) {
                permutes.last().add(permuteToRotate[index])
            }
            for (index in 0 until shift) {
                permutes.last().add(permuteToRotate[index])
            }
        }
    }
}