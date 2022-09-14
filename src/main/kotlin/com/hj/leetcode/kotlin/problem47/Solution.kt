package com.hj.leetcode.kotlin.problem47

/**
 * LeetCode page: [47. Permutations II](https://leetcode.com/problems/permutations-ii/);
 *
 * TODO 47-1 : Current implementation does not take effort to handle the duplication.
 * TODO 47-2 : Is it a good idea to handle repeated and distinct numbers separately?
 */
class Solution {
    /* Complexity:
     * Time O(N * N!) and Space O(N * N!) where N is the size of nums;
     */
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val permutes = mutableListOf(mutableListOf(nums[0]))

        for (index in 1..nums.lastIndex) {
            addNumberToEachPermute(nums[index], permutes)
            addRotationOfExistingPermutes(permutes)
        }

        val uniquePermutes = permutes.distinct()
        return uniquePermutes
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